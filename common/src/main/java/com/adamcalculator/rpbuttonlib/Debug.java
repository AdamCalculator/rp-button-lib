package com.adamcalculator.rpbuttonlib;

import com.adamcalculator.rpbuttonlib.screen.AbstractGenericItemEntry;
import com.adamcalculator.rpbuttonlib.screen.ButtonGenericItemEntry;
import com.adamcalculator.rpbuttonlib.screen.InjectorsList;
import com.adamcalculator.rpbuttonlib.screen.RPButtonLibScreen;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

public class Debug {
    public static void debugInit(RPButtonLib instance) {
        instance.addInjector(new ResourcePackEntryInjector() {
            @Override
            public boolean isInjectTo(ResourcePackArg resourcePack) {
                return new Random().nextBoolean();
            }

            @Override
            public boolean isSkipGenericScreenIfOnlyOne() {
                return true;
            }

            @Override
            public boolean isCustomIfOne() {
                return true;
            }

            @Override
            public void renderIfOne(ResourcePackArg resourcePackArg, GuiGraphics ctx, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta, CallbackInfo ci) {

            }

            @Override
            public void clickIfOne(ResourcePackArg resourcePackArg, double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {

            }

            @Override
            public void onClick(ResourcePackArg packArg) {
                RPButtonLib.LOGGER.info("click i1");
            }

            @Override
            public AbstractGenericItemEntry createGenericItemEntry(RPButtonLibScreen screen, InjectorsList injectorsList, ResourcePackArg resourcePackArg, ResourcePackEntryInjector[] injectors) {
                return new ButtonGenericItemEntry("i1", () -> {
                    onClick(resourcePackArg);
                });
            }
        });

        instance.addInjector(new ResourcePackEntryInjector() {
            @Override
            public boolean isInjectTo(ResourcePackArg resourcePack) {
                return true;
            }

            @Override
            public boolean isSkipGenericScreenIfOnlyOne() {
                return false;
            }

            @Override
            public boolean isCustomIfOne() {
                return false;
            }

            @Override
            public void renderIfOne(ResourcePackArg resourcePackArg, GuiGraphics ctx, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta, CallbackInfo ci) {

            }

            @Override
            public void clickIfOne(ResourcePackArg resourcePackArg, double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {

            }

            @Override
            public void onClick(ResourcePackArg packArg) {
                RPButtonLib.LOGGER.info("click i2");
            }

            @Override
            public AbstractGenericItemEntry createGenericItemEntry(RPButtonLibScreen screen, InjectorsList injectorsList, ResourcePackArg resourcePackArg, ResourcePackEntryInjector[] injectors) {
                return new ButtonGenericItemEntry("Hello i2", () -> onClick(resourcePackArg));
            }
        });
    }
}
