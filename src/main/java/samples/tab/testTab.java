package samples.tab;

import java.util.Iterator;
import java.util.List;

import com.webforj.App;
import com.webforj.component.Component;
import com.webforj.component.html.elements.Div;
import com.webforj.component.list.ChoiceBox;

import samples.tabInterface;

public class testTab implements tabInterface{
    private static testTab instance;
    private Boolean filled = false;
    public Div subFrame = new Div();

    private testTab() {
    }

    public Boolean isFilled() { return filled; }

    public Div getSubframe() { 
        App.consoleLog("making out div");
        Div out = new Div();
        List<Component> list = subFrame.getComponents();
        Iterator<Component> iter = list.iterator();
        while (iter.hasNext()) {
            out.add(iter.next());
        }
        out.addClassName("mainContent");
        return out; 
    }

    public static testTab getInstance() {
        if (instance == null) {
            instance = new testTab();
        }
        return instance;
    }

    public void run() {
        if (!filled) {
            ChoiceBox filters = new ChoiceBox();
            filters.add("All");
            filters.add("Rock");
            filters.add("Electro");
            filters.selectIndex(0)
                .setAttribute("width", "fit-content");
            subFrame.add(filters);
            filled = true;
        }
    } 
}
