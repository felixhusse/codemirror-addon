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

package de.fatalix.vaadin.addon.codemirror;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author felix.husse
 */
@JavaScript({"vaadin://codemirror/codemirror-compressed.js","codemirror-connector.js"})
@StyleSheet({"vaadin://codemirror/codemirror.css",
    "vaadin://codemirror/theme/themes.css",
    "vaadin://codemirror/fullscreen.css"})
public class CodeMirror extends AbstractJavaScriptComponent{
    private static int componentCount = 0;
    
    private String codeValue;
    private final int componentId;
    
    public CodeMirror() {
        super();
        componentId = componentCount;
        componentCount++;
        addFunction("onBlur", new JavaScriptFunction() {

            @Override
            public void call(JSONArray arguments) throws JSONException {
                codeValue = arguments.getString(0);
            }
        });
    }
    
    
    
    public void setCode(String value) {
        codeValue = value;
        CodeMirrorData data = new CodeMirrorData();
        data.code = value;
        data.id = componentId;
        data.state = "LOAD";
        getState().codeData = data;
    }
    
    public void setTheme(CodeMirrorTheme codeMirrorTheme) {
        CodeMirrorData data = new CodeMirrorData();
        data.state = "THEME";
        data.id = componentId;
        data.theme = codeMirrorTheme.getThemeName();
        getState().codeData = data;
    }
    
    public String getCode() {
        return codeValue;
    }
    
    
    @Override
    protected CodeMirrorState getState() {
        return (CodeMirrorState) super.getState(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
