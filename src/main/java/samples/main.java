package samples;


import com.webforj.App;
import com.webforj.component.button.Button;
import com.webforj.component.button.event.ButtonClickEvent;
import com.webforj.component.element.Element;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.Img;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.component.layout.applayout.AppLayout;
import com.webforj.component.window.Frame;
import com.webforj.exceptions.WebforjAppInitializeException;
import com.webforj.exceptions.WebforjException;

import samples.tab.testTab;

public class main extends App {
    private tabInterface tab;
    private String currentTab = "start";
    private Div content = new Div();

    //Frame window = adminTool.frame;
    AppLayout layout = new AppLayout();
    Frame window;
    Element css = new Element("style");
    Div header = new Div();
    Div drawer = new Div();

    public main() {
        try {
            window = new Frame();
            window.add(layout);
            window.add(css);
        } catch (WebforjAppInitializeException e) {
            App.consoleError(e.getMessage());
        }
    }

    @Override
    public void run() throws WebforjException {
        Button logout = new Button();
        logout.setText("Logout")
            .addClassName("tabButton");
        content.add(new Paragraph("test"));

        layout.setDrawerHeaderVisible(true)
            .setDrawerFooterVisible(true)
            .setDrawerOpened(true)
            .addToDrawerTitle( createLogoDiv() )
            .addToDrawerFooter(logout)
            .addToDrawer(drawer)
            .addToContent(content);

        header.addClassName("layout__header");

        layout.setHeaderOffscreen(false)
            .add(header);
        
        Button devices = new Button("Music");
        devices.addClassName("tabButton")
            .onClick(this::onTabChange);

        
        drawer.addClassName("drawerDiv")
            .add(
                devices
            );
    }

    private void onTabChange(ButtonClickEvent ev) {
        String title = ev.getComponent().getText();
        if (!title.equals(currentTab)) {
            if (title.equals("Music")) {
                tab = testTab.getInstance(); 
                handleContent(tab, title);
            }
            // ... other tabs would go here
        }
    }

    private void handleContent(tabInterface tab, String title) {
        if (!tab.isFilled()){ tab.run(); }
        layout.remove(content);
        content = tab.getSubframe();
        layout.add(content);
        currentTab = title;
    }

    private Div createLogoDiv() {
        Div logo = new Div().addClassName("drawer__logo");
        logo.add(new Img("https://documentation.webforj.com/img/webforj_icon.svg"));
        return logo;
    }
}

