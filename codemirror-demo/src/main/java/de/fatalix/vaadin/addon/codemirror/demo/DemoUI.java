package de.fatalix.vaadin.addon.codemirror.demo;


import javax.servlet.annotation.WebServlet;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import de.fatalix.vaadin.addon.codemirror.CodeMirror;
import de.fatalix.vaadin.addon.codemirror.CodeMirrorLanguage;
import de.fatalix.vaadin.addon.codemirror.CodeMirrorTheme;
import java.util.Arrays;


@Theme(ValoTheme.THEME_NAME)
@Title("CodeMirror Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    private static final String exampleCode =   "function findSequence(goal) {\n" +
                                                "  function find(start, history) {\n" +
                                                "    if (start == goal)\n" +
                                                "      return history;\n" +
                                                "    else if (start > goal)\n" +
                                                "      return null;\n" +
                                                "    else\n" +
                                                "      return find(start + 5, \"(\" + history + \" + 5)\") ||\n" +
                                                "             find(start * 3, \"(\" + history + \" * 3)\");\n" +
                                                "  }\n" +
                                                "  return find(1, \"1\");\n" +
                                                "}";
    
    private static final String exampleCode2 =   "function findDaSequenceeee(goal) {\n" +
                                                "  function doSomeSearching(start, history) {\n" +
                                                "  }\n" +
                                                "  return find(1, \"1\");\n" +
                                                "}";
    
    private static final String shortcutInfo = "+++++ Shortcuts +++++++++++++++++++++++\n"
                                             + "F11 \n"
                                             + "   Fullscreen\n"
                                             + "Ctrl-F \n"
                                             + "    Search\n"
                                             + "Ctrl-G \n"
                                             + "    Find next\n"
                                             + "Shift-Ctrl-G \n"
                                             + "    Find previous\n"
                                             + "Shift-Ctrl-F \n"
                                             + "    Replace\n"
                                             + "Shift-Ctrl-R \n"
                                             + "    Replace all\n"
                                             + "+++++ TERN Integration +++++++++++++++++++++++\n"
                                             + "Ctrl-Space \n"
                                             + "    Autocomplete\n"
                                             + "Ctrl-I \n"   
                                             + "    Find type at cursor\n"
                                             + "Alt-. \n"   
                                             + "    Jump to definition (Alt-, to jump back)\n"
                                             + "Ctrl-Q \n"   
                                             + "    Rename variable\n"
                                             + "Ctrl-. \n"   
                                             + "    Select all occurrences of a variable\n"
    
    ;
                                             
    private boolean changeFlag = false;
    
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = true, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(final VaadinRequest request) {
        try {
            
            // Show it in the middle of the screen
            final VerticalLayout layout = new VerticalLayout();
            layout.setStyleName("demoContentLayout");
            
            final CodeMirror codeMirror = new CodeMirror();
            codeMirror.setHeight(500, Unit.PIXELS);
            codeMirror.setWidth(600, Unit.PIXELS);
            codeMirror.setTheme(CodeMirrorTheme.THE_MATRIX);
            codeMirror.setCode(exampleCode);
            codeMirror.addBlurListener(new FieldEvents.BlurListener() {
                @Override
                public void blur(FieldEvents.BlurEvent event) {
                    Notification.show("Code Changed!",((CodeMirror)event.getComponent()).getCode(), Notification.Type.TRAY_NOTIFICATION);
                }
            });
            
            Button button = new Button("Load some code", new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    if (changeFlag) {
                        codeMirror.setCode(exampleCode);
                        changeFlag = false;
                    }
                    else {
                        codeMirror.setCode(exampleCode2);
                        changeFlag = true;
                    }
                }
            });
            
            Button showCode = new Button("Show Code", new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    Window dialog = new Window("Code:");
                    dialog.setModal(true);
                    dialog.setContent(new VerticalLayout(new Label(codeMirror.getCode(), ContentMode.PREFORMATTED)));
                    dialog.setHeight(400,Unit.PIXELS);
                    dialog.setWidth(500,Unit.PIXELS);
                    UI.getCurrent().addWindow(dialog);
                }
            });
            
            final Button ternOnOff = new Button("Enable Tern", new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    if (event.getButton().getCaption().contains("Enable")) {
                        event.getButton().setCaption("Disable Tern");
                        codeMirror.setTernMode(true);
                    }
                    else {
                        event.getButton().setCaption("Enable Tern");
                        codeMirror.setTernMode(false);
                    }
                }
            });
            
            
            final ComboBox themeSelect = new ComboBox(null,Arrays.asList(CodeMirrorTheme.values()));
            themeSelect.setValue(CodeMirrorTheme.DEFAULT);
            themeSelect.setImmediate(true);
            themeSelect.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    CodeMirrorTheme theme = (CodeMirrorTheme)event.getProperty().getValue();
                    Notification.show(theme.getThemeName());
                    codeMirror.setTheme(theme);
                }
            });
            
            final ComboBox languageSelect = new ComboBox(null, Arrays.asList(CodeMirrorLanguage.values()));
            languageSelect.setValue(CodeMirrorLanguage.DEFAULT);
            languageSelect.setImmediate(true);
            languageSelect.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    CodeMirrorLanguage langauge = (CodeMirrorLanguage)event.getProperty().getValue();
                    Notification.show(langauge.getLanguageName());
                    codeMirror.setLanguage(langauge);
                }
            });
            
            HorizontalLayout buttonLayout = new HorizontalLayout(button,showCode,ternOnOff,themeSelect,languageSelect);
            
            
            Label label = new Label(shortcutInfo, ContentMode.PREFORMATTED);
            label.setSizeUndefined();
            
            HorizontalLayout contentLayout = new HorizontalLayout(label,codeMirror);
            contentLayout.setExpandRatio(codeMirror, 1.0f);
            contentLayout.setWidth(100, Unit.PERCENTAGE);
            
            layout.addComponents(buttonLayout,contentLayout);
            layout.setExpandRatio(contentLayout, 1.0f);
            layout.setComponentAlignment(contentLayout, Alignment.MIDDLE_CENTER);
            layout.setSizeFull();
            
            
            setContent(layout);

        } catch(Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
