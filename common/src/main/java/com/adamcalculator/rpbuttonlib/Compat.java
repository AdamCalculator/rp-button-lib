package com.adamcalculator.rpbuttonlib;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class Compat {
    public static void drawTexture(GuiGraphics context, ResourceLocation texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
        //RenderSystem.setShaderTexture(0, texture);
        context.blit(texture, x, y, u, v, width, height, textureWidth, textureHeight);
    }

    public static <T extends GuiEventListener & Renderable & NarratableEntry> T createButton(Component text, Runnable press, int w, int h, int x, int y) {
        return (T) Button.builder(text, button -> press.run()).size(w, h).pos(x, y).build();
    }
}
