package com.adamcalculator.rpbuttonlib.mixin;

import com.adamcalculator.rpbuttonlib.RPButtonLib;
import com.adamcalculator.rpbuttonlib.ResourcePackArg;
import com.adamcalculator.rpbuttonlib.ResourcePackEntryInjector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.packs.PackSelectionModel;
import net.minecraft.client.gui.screens.packs.TransferableSelectionList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * For integrate button to ResourcePacks screen
 */
@Mixin(value = TransferableSelectionList.PackEntry.class, priority = 9999)
public abstract class ResourcePackEntryMixin {
    @Shadow @Final private TransferableSelectionList parent;
    @Unique private ResourcePackArg rpbuttonlib$resourcepackArg;
    @Unique private ResourcePackEntryInjector[] rpbuttonlib$injectors = null;
    @Unique private long rpbuttonlib$injectorsCounter = -1;

    @Unique
    private void rpbuttonlib$updateInjectors() {
        RPButtonLib instance = RPButtonLib.getInstance();
        long actualInjectorsCounter;
        if (rpbuttonlib$injectorsCounter != (actualInjectorsCounter = instance.getInjectorsCounter())) {
            rpbuttonlib$injectors = instance.getInjectorsFor(rpbuttonlib$resourcepackArg);
            rpbuttonlib$injectorsCounter = actualInjectorsCounter;
        }
    }

    @Inject(at = @At("RETURN"), method = "<init>")
    private void rpbuttonlib$init(Minecraft minecraft, TransferableSelectionList transferableSelectionList, PackSelectionModel.Entry entry, CallbackInfo ci) {
        rpbuttonlib$resourcepackArg = ResourcePackArg.createInternal(this, parent);
        rpbuttonlib$updateInjectors();
    }

    @Inject(at = @At("RETURN"), method = "render")
    private void rpbuttonlib$render(GuiGraphics ctx, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta, CallbackInfo ci) {
        rpbuttonlib$updateInjectors();
        RPButtonLib.getInstance().renderResourcePackEntry(rpbuttonlib$resourcepackArg, rpbuttonlib$injectors, ctx, index, y, x, entryWidth, entryHeight, mouseX, mouseY, hovered, tickDelta, ci);
    }

    @Inject(at = @At("RETURN"), method = "mouseClicked")
    private void rpbuttonlib$mouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        rpbuttonlib$updateInjectors();
        RPButtonLib.getInstance().mouseClicked(rpbuttonlib$resourcepackArg, rpbuttonlib$injectors, mouseX, mouseY, button, cir);
    }
}
