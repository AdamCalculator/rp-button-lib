package com.adamcalculator.rpbuttonlib;

import com.adamcalculator.rpbuttonlib.screen.AbstractGenericItemEntry;
import com.adamcalculator.rpbuttonlib.screen.InjectorsList;
import com.adamcalculator.rpbuttonlib.screen.RPButtonLibScreen;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public interface ResourcePackEntryInjector {
    /**
     * Is need inject to resourcepack
     * @see ResourcePackArg
     */
    boolean isInjectTo(ResourcePackArg resourcePack);

    boolean isSkipGenericScreenIfOnlyOne();

    /**
     * Is use {@link #renderIfOne(ResourcePackArg, GuiGraphics, int, int, int, int, int, int, int, boolean, float, CallbackInfo)} and {@link #clickIfOne(ResourcePackArg, double, double, int, CallbackInfoReturnable)}
     */
    boolean isCustomIfOne();

    /**
     * Raw data from mixin if your mod is the only one and is {@link #isCustomIfOne()}
     */
    void renderIfOne(ResourcePackArg resourcePackArg, GuiGraphics ctx, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta, CallbackInfo ci);

    /**
     * Raw data from mixin if your mod is the only one and is {@link #isCustomIfOne()}
     */
    void clickIfOne(ResourcePackArg resourcePackArg, double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir);

    /**
     * Called if your mod not only one and user selected your mod
     */
    void onClick(ResourcePackArg packArg);


    // todo java doc
    @NotNull
    AbstractGenericItemEntry createGenericItemEntry(RPButtonLibScreen screen, InjectorsList injectorsList, ResourcePackArg resourcePackArg, ResourcePackEntryInjector[] injectors);
}
