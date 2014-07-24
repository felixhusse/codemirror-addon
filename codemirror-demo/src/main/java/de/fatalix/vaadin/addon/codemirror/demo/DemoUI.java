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
import de.fatalix.vaadin.addon.codemirror.CodeMirror;
import de.fatalix.vaadin.addon.codemirror.CodeMirrorLanguage;
import de.fatalix.vaadin.addon.codemirror.CodeMirrorTheme;
import java.util.Arrays;


@Theme("demo")
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
    
    private boolean changeFlag = false;
    
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
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
            
            HorizontalLayout buttonLayout = new HorizontalLayout(button,showCode,themeSelect,languageSelect);
            layout.addComponents(buttonLayout,codeMirror);
            layout.setExpandRatio(codeMirror, 1.0f);
            layout.setComponentAlignment(codeMirror, Alignment.MIDDLE_CENTER);
            layout.setSizeFull();
            
            
            setContent(layout);

        } catch(Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
