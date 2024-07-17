package com.adamcalculator.rpbuttonlib.screen;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ButtonGenericItemEntry extends AbstractGenericItemEntry {
    protected Button button;

    public ButtonGenericItemEntry(String text, Runnable click) {
        this.button = Button.builder(Component.literal(text), button -> click.run()).bounds(0, 0, 100, 20).build();
    }

    public @NotNull List<? extends GuiEventListener> children() {
        return ImmutableList.of(this.button);
    }

    public @NotNull List<? extends NarratableEntry> narratables() {
        return ImmutableList.of(this.button);
    }

    @Override
    public void render(GuiGraphics context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
        this.button.setX(x + entryWidth - 140);
        this.button.setY(y);
        this.button.render(context, mouseX, mouseY, tickDelta);
    }
}
