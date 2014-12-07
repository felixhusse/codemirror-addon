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

import java.io.Serializable;

/**
 *
 * @author felix
 */
public class CodeMirrorData implements Serializable {
    public String state;
    public String theme = "default";
    public String mode = "javascript";
    public String code = "//please start to code";
    public String height = "600";
    public String width = "600";
    public int id;
    public boolean ternEnabled = false;
}
