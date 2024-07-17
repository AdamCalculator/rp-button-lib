package com.adamcalculator.rpbuttonlib.screen;

import com.adamcalculator.rpbuttonlib.ResourcePackArg;
import com.adamcalculator.rpbuttonlib.ResourcePackEntryInjector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;

public class InjectorsList extends ContainerObjectSelectionList<AbstractGenericItemEntry> {
    public InjectorsList(RPButtonLibScreen parent, Minecraft minecraft, ResourcePackArg resourcePackArg, ResourcePackEntryInjector[] injectors) {
        super(minecraft, parent.width, parent.height - 52, 20, 40);

        for (ResourcePackEntryInjector injector : injectors) {
            addEntry(injector.createGenericItemEntry(parent, this, resourcePackArg, injectors));
        }
    }

    protected int getScrollbarPosition() {
        return super.getScrollbarPosition() + 15;
    }

    @Override
    public int getRowWidth() {
        return super.getRowWidth() + 32;
    }
}
