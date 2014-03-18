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

    var INIT = "init";
    var LOAD = "load";

    var codemirror;
    var e = this.getElement();
    var self = this;
    
    this.onStateChange = function() {
        var state = this.getState();
        if (state.codeData.state === 'LOAD') {
            if (typeof codemirror == 'undefined') {
                e.innerHTML = e.innerHTML + "<div id='cm-addon-"+state.codeData.id+"'></div>";
                codemirror = CodeMirror(document.getElementById('cm-addon-'+state.codeData.id), {
                    value: state.codeData.code,
                    mode: "javascript",
                    lineNumbers: true,
                    theme: "mbo",
                    extraKeys: {
                        "F11": function(cm) {
                            cm.setOption("fullScreen", !cm.getOption("fullScreen"));
                        },
                        "Esc": function(cm) {
                            if (cm.getOption("fullScreen"))
                                cm.setOption("fullScreen", false);
                        }
                    }
                });

                codemirror.on("blur", function() {
                   var value = codemirror.getValue();
                   self.onBlur(value);
                });    
            }
            else {
                codemirror.setValue(state.codeData.code);
            }
                
        }
        
        
    };

};

