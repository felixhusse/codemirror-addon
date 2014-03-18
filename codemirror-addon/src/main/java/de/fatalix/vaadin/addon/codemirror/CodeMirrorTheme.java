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
public enum CodeMirrorTheme {
    DEFAULT("default"),
    DAY_3024("3024-day"),
    NIGHT_3024("3024-night"),
    AMBIANCE("ambiance"),
    BASE16_DARK("base16-dark"),
    BASE16_LIGHT("base16-light"),
    BLACKBOARD("blackboard"),
    COBALT("cobalt"),
    ECLIPSE("eclipse"),
    ELEGANT("elegant"),
    ERLANG_DARK("erlang-dark"),
    ERLANG_LIGHT("erlang-light"),
    LESSER_DARK("lesser-dark"),
    MBO("mbo"),
    MDN_LIKE("mdn-like"),
    MIDNIGHT("midnight"),
    MONOKAI("monokai"),
    NEAT("neat"),
    NIGHT("night"),
    PARAISO_DARK("paraiso-dark"),
    PARAISO_LIGHT("paraiso-light"),
    PASTEL_ON_DARK("paster-on-dark"),
    RUBYBLUE("rubyblue"),
    SOLARIZED_DARK("solarized dark"),
    SOLARIZED_LIGHT("solarized light"),
    THE_MATRIX("the-matrix"),
    TOMORROW_NIGHT_EIGHTIES("tomorrow-night-eighties"),
    TWILIGHT("twilight"),
    VIBRANT_INK("vibrant-ink"),
    XQ_DARK("xq-dark"),
    XQ_LIGHT("xq-light");
    
    
    private final String themeName;

    private CodeMirrorTheme(String themeName) {
        this.themeName = themeName;
    }
    
    public String getThemeName() {
        return themeName;
    }

    @Override
    public String toString() {
        return themeName;
    }
    
    
}
