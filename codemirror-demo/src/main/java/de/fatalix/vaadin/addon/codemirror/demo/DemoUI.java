package de.fatalix.vaadin.addon.codemirror.demo;


import javax.servlet.annotation.WebServlet;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.fatalix.vaadin.addon.codemirror.CodeMirror;


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
            
            Button button = new Button("Load some code", new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    codeMirror.setCode(exampleCode2);
                    
//                    Window dialog = new Window("Code:");
//                    dialog.setModal(true);
//                    dialog.setContent(new VerticalLayout(new Label(codeMirror.getCode(), ContentMode.PREFORMATTED)));
//                    dialog.setHeight(400,Unit.PIXELS);
//                    dialog.setWidth(500,Unit.PIXELS);
//                    UI.getCurrent().addWindow(dialog);
                }
            });
            
            layout.addComponents(codeMirror,button);
            layout.setExpandRatio(codeMirror, 1.0f);
            layout.setComponentAlignment(codeMirror, Alignment.MIDDLE_CENTER);
            layout.setSizeFull();
            
            
            setContent(layout);

        } catch(Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
