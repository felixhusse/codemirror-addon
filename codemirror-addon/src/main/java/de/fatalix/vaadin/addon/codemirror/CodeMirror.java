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
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author felix.husse
 */
@JavaScript({"vaadin://codemirror/codemirror-4.8-compressed.js","codemirror-connector.js"})
@StyleSheet({"vaadin://codemirror/codemirror.css","vaadin://codemirror/theme/themes.css",
    "vaadin://codemirror/fullscreen.css","vaadin://codemirror/tern.css","vaadin://codemirror/dialog.css"})
public class CodeMirror extends AbstractJavaScriptComponent{
    private static int componentCount = 0;
    
    private CodeMirrorData codeMirrorData;
    private String codeValue;
    private final int componentId;
    
    private List<FieldEvents.BlurListener> blurListeners = new ArrayList<>();
    
    public CodeMirror() {
        super();
        
        componentId = componentCount;
        componentCount++;
        
        CodeMirrorData codeMirrorData = new CodeMirrorData();
        codeMirrorData.id = componentId;
        codeMirrorData.mode = "javascript";
        codeMirrorData.theme = "default";
        codeMirrorData.code = "//start to code";
        
        addFunction("onBlur", new JavaScriptFunction() {

            @Override
            public void call(JSONArray arguments) throws JSONException {
                codeValue = arguments.getString(0);
                for (FieldEvents.BlurListener listener : blurListeners) {
                    listener.blur(new FieldEvents.BlurEvent(CodeMirror.this));
                }
            }
        });
        getState().codeData = codeMirrorData;
    }
    
    @Override
    public void setWidth(String width) {
        super.setWidth(width);
        getState().codeData.width = width;
    }

    @Override
    public void setHeight(String height) {
        super.setHeight(height);
        getState().codeData.height = height;
    }
    
    @Override
    public void setHeight(float height, Unit unit) {
        super.setHeight(height, unit);
        getState().codeData.height = height + unit.getSymbol();
    }

    @Override
    public void setWidth(float width, Unit unit) {
        super.setWidth(width, unit);
        getState().codeData.width = width + unit.getSymbol();

    }
    
    public void setCode(String value) {
        codeValue = value;

        getState().codeData.code = value;
        getState().codeData.id = componentId;
        getState().codeData.state = "LOAD";

    }
    
    public void setTheme(CodeMirrorTheme codeMirrorTheme) {

        getState().codeData.state = "THEME";
        getState().codeData.id = componentId;
        getState().codeData.theme = codeMirrorTheme.getThemeName();

    }
    
    public void setLanguage(CodeMirrorLanguage codeMirrorLanguage) {

        getState().codeData.state = "MODE";
        getState().codeData.id = componentId;
        getState().codeData.mode = codeMirrorLanguage.getLanguageName();

    }
    
    public String getCode() {
        return codeValue;
    }
    
    public void addBlurListener(FieldEvents.BlurListener listener) {
        blurListeners.add(listener);
    }
    
    public void removeBlurListener(FieldEvents.BlurListener listener) {
        blurListeners.remove(listener);
    }
    
    @Override
    protected CodeMirrorState getState() {
        return (CodeMirrorState) super.getState();
    }
    
    
    
}
