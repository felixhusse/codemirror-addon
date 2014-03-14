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

/**
 *
 * @author felix.husse
 */
@JavaScript({"vaadin://codemirror/codemirror-compressed.js","codemirror-connector.js"})
@StyleSheet({"vaadin://codemirror/codemirror.css","vaadin://codemirror/theme/ambiance.css","vaadin://codemirror/theme/mbo.css","vaadin://codemirror/fullscreen.css"})
public class CodeMirror extends AbstractJavaScriptComponent{
    
    
    public void setCode(String value) {
        getState().codeString = value;
    }
    
    public String getCode() {
        return getState().codeString;
    }

    @Override
    protected CodeMirrorState getState() {
        return (CodeMirrorState) super.getState(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
