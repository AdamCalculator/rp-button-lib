package com.adamcalculator.rpbuttonlib.screen;

import com.adamcalculator.rpbuttonlib.Compat;
import com.adamcalculator.rpbuttonlib.ResourcePackArg;
import com.adamcalculator.rpbuttonlib.ResourcePackEntryInjector;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class RPButtonLibScreen extends Screen {
    private final Screen previous;
    private final ResourcePackArg resourcePackArg;
    private final ResourcePackEntryInjector[] injectors;
    private InjectorsList injectorsList;

    public RPButtonLibScreen(Screen previous, ResourcePackArg resourcePackArg, ResourcePackEntryInjector[] injectors) {
        super(Component.literal("Select..."));
        this.previous = previous;
        this.resourcePackArg = resourcePackArg;
        this.injectors = injectors;
    }


    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        injectorsList.render(context, mouseX, mouseY, delta);
    }

    @Override
    protected void init() {
        super.init();
        this.addWidget(this.injectorsList = new InjectorsList(this, this.minecraft, resourcePackArg, injectors));
        this.addRenderableWidget(Compat.createButton(CommonComponents.GUI_DONE, this::onClose, 150, 20, this.width / 2 - 155 + 160, this.height - 29));
    }

    @Override
    public void onClose() {
        minecraft.setScreen(previous);
    }
}
