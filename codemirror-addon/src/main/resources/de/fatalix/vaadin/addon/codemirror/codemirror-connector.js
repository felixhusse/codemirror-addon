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
    var e = this.getElement();
    
    this.onStateChange = function() {
        var state = this.getState();
        var codeString = state.codeString;
        e.innerHTML = "<div id='codemirror-addon'></div>";
        codemirror = CodeMirror(document.getElementById('codemirror-addon'), {
            value: codeString,
            mode:  "javascript",
            lineNumbers: true,
            theme: "mbo",
            extraKeys: {
              "F11": function(cm) {
                cm.setOption("fullScreen", !cm.getOption("fullScreen"));
              },
              "Esc": function(cm) {
                if (cm.getOption("fullScreen")) cm.setOption("fullScreen", false);
              }
            }
          });
    };
    
    this.loadCode = function(codeString) {
        
    }
};

