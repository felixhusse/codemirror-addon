/* 
 * Copyright 2014 fatalix.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
window.de_fatalix_vaadin_addon_codemirror_CodeMirror = function() {
    var codemirror;
    var ternServer;
    var e = this.getElement();
    var self = this;
    var currentCodeData;
    
    
    this.onStateChange = function() {
        var state = this.getState();
        
        initCodeMirror(state.codeData.id);
        modifyCodeMirrorState(state.codeData);
        
    };
    
    modifyCodeMirrorState = function(codeData) {
        if (typeof currentCodeData !== 'undefined') {
            if (currentCodeData.code !== codeData.code) {
                codemirror.setValue(codeData.code);
            }
            if (currentCodeData.theme !== codeData.theme) {
                codemirror.setOption("theme", codeData.theme);
            }
            if (currentCodeData.mode !== codeData.mode) {
                codemirror.setOption("mode", codeData.mode);
            }
            if (currentCodeData.width !== codeData.width || currentCodeData.height !== codeData.height) {
            	 codemirror.setSize(codeData.width,codeData.height);
            }
        }
        else {
            codemirror.setValue(codeData.code);
            codemirror.setOption("theme", codeData.theme);
            codemirror.setOption("mode", codeData.mode);
            codemirror.setSize(codeData.width,codeData.height);
        }
        currentCodeData = codeData;
    };
    
    initCodeMirror = function(id) {
        if (typeof codemirror === 'undefined') {
            e.innerHTML = e.innerHTML + "<div id='cm-addon-" + id + "'></div>";
            codemirror = CodeMirror(document.getElementById('cm-addon-' + id), {
                value: "",
                mode: "javascript",
                lineNumbers: true,
                theme: "default"
            });
            
            codemirror.on("blur", function() {
                var value = codemirror.getValue();
                self.onBlur(value);
            });
            getURL("http://ternjs.net/defs/ecma5.json", function(err, code) {
                if (err) throw new Error("Request for ecma5.json: " + err);
                ternServer = new CodeMirror.TernServer({defs: [JSON.parse(code)]});
                codemirror.setOption("extraKeys", {
                  "Ctrl-Space": function(cm) { if(currentCodeData.ternEnabled){ternServer.complete(cm);}},
                  "Ctrl-I": function(cm) { if(currentCodeData.ternEnabled){ternServer.showType(cm);} },
                  "Alt-.": function(cm) { if(currentCodeData.ternEnabled){ternServer.jumpToDef(cm);} },
                  "Alt-,": function(cm) { if(currentCodeData.ternEnabled){ternServer.jumpBack(cm);} },
                  "Ctrl-Q": function(cm) { if(currentCodeData.ternEnabled){ternServer.rename(cm);} },
                  "Ctrl-.": function(cm) { if(currentCodeData.ternEnabled){ternServer.selectName(cm);} },
                  "F11": function(cm) {cm.setOption("fullScreen", !cm.getOption("fullScreen"));},
                  "Esc": function(cm) {if (cm.getOption("fullScreen"))cm.setOption("fullScreen", false);}
                });
                codemirror.on("cursorActivity", function(cm) { if(currentCodeData.ternEnabled){ternServer.updateArgHints(cm);} });
              });
        }
    };
    
    
    
    function getURL(url, c) {
        var xhr = new XMLHttpRequest();
        xhr.open("get", url, true);
        xhr.send();
        xhr.onreadystatechange = function() {
          if (xhr.readyState != 4) return;
          if (xhr.status < 400) return c(null, xhr.responseText);
          var e = new Error(xhr.responseText || "No response");
          e.status = xhr.status;
          c(e);
        };
      }
    
};

