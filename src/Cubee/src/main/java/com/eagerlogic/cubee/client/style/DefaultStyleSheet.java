/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.cubee.client.style;

import com.eagerlogic.cubee.client.components.AComponent;
import com.eagerlogic.cubee.client.components.ALayout;
import com.eagerlogic.cubee.client.components.AMenuItem;
import com.eagerlogic.cubee.client.components.AUserControl;
import com.eagerlogic.cubee.client.components.AFillView;
import com.eagerlogic.cubee.client.components.Button;
import com.eagerlogic.cubee.client.components.CheckBox;
import com.eagerlogic.cubee.client.components.CollapseLabel;
import com.eagerlogic.cubee.client.components.ComboBox;
import com.eagerlogic.cubee.client.components.HBox;
import com.eagerlogic.cubee.client.components.HTMLComponent;
import com.eagerlogic.cubee.client.components.Hyperlink;
import com.eagerlogic.cubee.client.components.Label;
import com.eagerlogic.cubee.client.components.Led;
import com.eagerlogic.cubee.client.components.Panel;
import com.eagerlogic.cubee.client.components.PasswordBox;
import com.eagerlogic.cubee.client.components.PictureBox;
import com.eagerlogic.cubee.client.components.ScrollPanel;
import com.eagerlogic.cubee.client.components.TextArea;
import com.eagerlogic.cubee.client.components.TextBox;
import com.eagerlogic.cubee.client.components.VBox;
import com.eagerlogic.cubee.client.components.ViewSwitcher;
import com.eagerlogic.cubee.client.style.styles.ABackground;
import com.eagerlogic.cubee.client.style.styles.Border;
import com.eagerlogic.cubee.client.style.styles.Color;
import com.eagerlogic.cubee.client.style.styles.ColorStop;
import com.eagerlogic.cubee.client.style.styles.LinearGradient;
import com.eagerlogic.cubee.client.style.styles.Padding;

/**
 *
 * @author dipacs
 */
public class DefaultStyleSheet extends StyleSheet {

    public DefaultStyleSheet() {
        this.setStyle(AComponent.class, AComponentStyle());
        this.setStyle(ALayout.class, ALayoutStyle());
        this.setStyle(AMenuItem.class, AMenuItemStyle());
        this.setStyle(AUserControl.class, AUserControlStyle());
        this.setStyle(AFillView.class, AViewStyle());
        this.setStyle(Button.class, ButtonStyle());
        this.setStyle(CheckBox.class, CheckBoxStyle());
        this.setStyle(CollapseLabel.class, CollapseLabelStyle());
        this.setStyle(ComboBox.class, ComboBoxStyle());
        this.setStyle(HBox.class, HBoxStyle());
        this.setStyle(HTMLComponent.class, HTMLComponentStyle());
        this.setStyle(Hyperlink.class, HyperlinkStyle());
        this.setStyle(Label.class, LabelStyle());
        this.setStyle(Led.class, LedStyle());
        this.setStyle(Panel.class, PanelStyle());
        this.setStyle(PasswordBox.class, PasswordBoxStyle());
        this.setStyle(PictureBox.class, PictureBoxStyle());
        this.setStyle(ScrollPanel.class, ScrollPanelStyle());
        this.setStyle(TextArea.class, TextAreaStyle());
        this.setStyle(TextBox.class, TextBoxStyle());
        this.setStyle(VBox.class, VBoxStyle());
        this.setStyle(ViewSwitcher.class, ViewSwitcherStyle());

    }

    private AComponent.StyleClass<AComponent> AComponentStyle() {
        AComponent.StyleClass<AComponent> res = new AComponent.StyleClass<AComponent>();
        return res;
    }

    private ALayout.StyleClass<ALayout> ALayoutStyle() {
        ALayout.StyleClass<ALayout> res = new ALayout.StyleClass<ALayout>();
        return res;
    }

    private AMenuItem.StyleClass<AMenuItem> AMenuItemStyle() {
        AMenuItem.StyleClass<AMenuItem> res = new AMenuItem.StyleClass<AMenuItem>();
        return res;
    }

    private AUserControl.StyleClass<AUserControl> AUserControlStyle() {
        AUserControl.StyleClass<AUserControl> res = new AUserControl.StyleClass<AUserControl>();
        return res;
    }

    private AFillView.StyleClass<AFillView> AViewStyle() {
        AFillView.StyleClass<AFillView> res = new AFillView.StyleClass<AFillView>();
        return res;
    }

    private Button.StyleClass<Button> ButtonStyle() {
        Button.StyleClass<Button> res = new Button.StyleClass<Button>();
        LinearGradient bg = new LinearGradient(0.0, new ColorStop(0.0, Color.FUNKY_BLUE), new ColorStop(1.0, Color.fadeColors(Color.BLACK, Color.FUNKY_BLUE, 0.9)));
        res.getBackground().setValue(new StyleValue<ABackground>(bg));
        res.getPadding().setValue(new StyleValue<Padding>(new Padding(10, 5, 10, 5)));
        res.getBorder().setValue(new StyleValue<Border>(new Border(1, Color.fadeColors(Color.BLACK, Color.FUNKY_BLUE, 0.85), 0)));
        res.getForeColor().setValue(new StyleValue<Color>(Color.WHITE));
        res.getFontSize().setValue(new StyleValue<Integer>(14));
        res.getBold().setValue(new StyleValue<Boolean>(Boolean.TRUE));
        return res;
    }

    private CheckBox.StyleClass<CheckBox> CheckBoxStyle() {
        CheckBox.StyleClass<CheckBox> res = new CheckBox.StyleClass<CheckBox>();
        return res;
    }

    private CollapseLabel.StyleClass<CollapseLabel> CollapseLabelStyle() {
        CollapseLabel.StyleClass<CollapseLabel> res = new CollapseLabel.StyleClass<CollapseLabel>();
        return res;
    }

    private ComboBox.StyleClass<ComboBox> ComboBoxStyle() {
        ComboBox.StyleClass<ComboBox> res = new ComboBox.StyleClass<ComboBox>();
        LinearGradient bg = new LinearGradient(0.0, new ColorStop(0.0, Color.WHITE), new ColorStop(1.0, Color.getRgbColor(0xf0f0f0)));
        res.getBackground().setValue(new StyleValue<ABackground>(bg));
        res.getPadding().setValue(new StyleValue<Padding>(new Padding(10, 5, 10, 5)));
        res.getBorder().setValue(new StyleValue<Border>(new Border(1, Color.LIGHT_GRAY, 0)));
        return res;
    }

    private HBox.StyleClass<HBox> HBoxStyle() {
        HBox.StyleClass<HBox> res = new HBox.StyleClass<HBox>();
        return res;
    }

    private HTMLComponent.StyleClass<HTMLComponent> HTMLComponentStyle() {
        HTMLComponent.StyleClass<HTMLComponent> res = new HTMLComponent.StyleClass<HTMLComponent>();
        return res;
    }

    private Hyperlink.StyleClass<Hyperlink> HyperlinkStyle() {
        Hyperlink.StyleClass<Hyperlink> res = new Hyperlink.StyleClass<Hyperlink>();
        return res;
    }

    private Label.StyleClass<Label> LabelStyle() {
        Label.StyleClass<Label> res = new Label.StyleClass<Label>();
        return res;
    }

    private Led.StyleClass<Led> LedStyle() {
        Led.StyleClass<Led> res = new Led.StyleClass<Led>();
        return res;
    }

    private Panel.StyleClass<Panel> PanelStyle() {
        Panel.StyleClass<Panel> res = new Panel.StyleClass<Panel>();
        return res;
    }

    private PasswordBox.StyleClass<PasswordBox> PasswordBoxStyle() {
        PasswordBox.StyleClass<PasswordBox> res = new PasswordBox.StyleClass<PasswordBox>();
        return res;
    }

    private PictureBox.StyleClass<PictureBox> PictureBoxStyle() {
        PictureBox.StyleClass<PictureBox> res = new PictureBox.StyleClass<PictureBox>();
        return res;
    }

    private ScrollPanel.StyleClass<ScrollPanel> ScrollPanelStyle() {
        ScrollPanel.StyleClass<ScrollPanel> res = new ScrollPanel.StyleClass<ScrollPanel>();
        return res;
    }

    private TextArea.StyleClass<TextArea> TextAreaStyle() {
        TextArea.StyleClass<TextArea> res = new TextArea.StyleClass<TextArea>();
        return res;
    }

    private TextBox.StyleClass<TextBox> TextBoxStyle() {
        TextBox.StyleClass<TextBox> res = new TextBox.StyleClass<TextBox>();
        res.getPadding().setValue(new StyleValue<Padding>(new Padding(5)));
        res.getBorder().setValue(new StyleValue<Border>(new Border(1, Color.LIGHT_GRAY, 0)));
        return res;
    }

    private VBox.StyleClass<VBox> VBoxStyle() {
        VBox.StyleClass<VBox> res = new VBox.StyleClass<VBox>();
        return res;
    }

    private ViewSwitcher.StyleClass<ViewSwitcher> ViewSwitcherStyle() {
        ViewSwitcher.StyleClass<ViewSwitcher> res = new ViewSwitcher.StyleClass<ViewSwitcher>();
        return res;
    }

}
