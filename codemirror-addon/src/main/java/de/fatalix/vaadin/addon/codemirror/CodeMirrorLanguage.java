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

/**
 *
 * @author felix.husse
 */
public enum CodeMirrorLanguage {
    DEFAULT("javascript"),
    XML("application/xml"),
    HTML("text/html"),
    SQL("text/x-sql"),
    JAVA("text/x-java");
    
    
    private final String languageName;

    private CodeMirrorLanguage(String languageName) {
        this.languageName = languageName;
    }
    
    public String getLanguageName() {
        return languageName;
    }

    @Override
    public String toString() {
        return languageName;
    }
    
    
}
