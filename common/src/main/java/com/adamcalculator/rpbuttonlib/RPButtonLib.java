package com.adamcalculator.rpbuttonlib;

import com.adamcalculator.rpbuttonlib.screen.RPButtonLibScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


public class RPButtonLib {
    private static final RPButtonLib INSTANCE = new RPButtonLib();
    protected static final Logger LOGGER = Logger.getLogger("RPButtonLib");
    private static final ResourceLocation BUTTON_TEXTURE = ResourceLocation.tryBuild("rpbuttonlib", "configure_button.png");

    private final Set<ResourcePackEntryInjector> injectors = new HashSet<>();

    // for monitor updates (one update +1 to this counter)
    private long injectorsCounter = 0;

    public static RPButtonLib getInstance() {
        return INSTANCE;
    }

    public RPButtonLib() {
        Debug.debugInit(this);
    }

    @NotNull
    private ResourcePackEntryInjector getFirstInjector(ResourcePackEntryInjector[] injectors) {
        if (injectors.length > 0) {
            return injectors[0];
        }

        throw new IllegalArgumentException("rp-button-lib: getFirstInjector variable 'injectors' can't be empty!");
    }

    public ResourcePackEntryInjector[] getInjectorsFor(ResourcePackArg resourcePackArg) {
        Set<ResourcePackEntryInjector> found = new HashSet<>();
        for (ResourcePackEntryInjector injector : injectors) {
            if (injector.isInjectTo(resourcePackArg)) {
                found.add(injector);
            }
        }
        return found.toArray(new ResourcePackEntryInjector[0]);
    }

    public void renderResourcePackEntry(ResourcePackArg resourcePackArg, ResourcePackEntryInjector[] injectors, GuiGraphics ctx, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta, CallbackInfo ci) {
        int count = injectors.length;
        ResourcePackEntryInjector firstInjector;
        if (count == 1 && (firstInjector = getFirstInjector(injectors)).isCustomIfOne()) {
            firstInjector.renderIfOne(resourcePackArg, ctx, index, y, x, entryWidth, entryHeight, mouseX, mouseY, hovered, tickDelta, ci);

        } else if (count != 0) {
            Compat.drawTexture(ctx, BUTTON_TEXTURE, x + 171, y + 13, 0.0F, (((mouseX - x) >= 171 && (mouseY - y) >= 13 && hovered) ? 20f : 0f), 20, 20, 32, 64);
        }
    }

    public void mouseClicked(ResourcePackArg resourcePackArg, ResourcePackEntryInjector[] injectors, double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        int count = injectors.length;
        ResourcePackEntryInjector firstInjector = null;
        if (count == 1 && (firstInjector = getFirstInjector(injectors)).isCustomIfOne()) {
            firstInjector.clickIfOne(resourcePackArg, mouseX, mouseY, button, cir);

        } else if (count != 0) {
            double d = mouseX - (double)resourcePackArg.getList().getRowLeft();
            double e = mouseY - (double)resourcePackArg.getList().getRowTop(resourcePackArg.getList().children().indexOf(resourcePackArg.getPackEntry()));

            if (d >= 171) {
                if (e >= 13) {
                    if (count == 1 && firstInjector.isSkipGenericScreenIfOnlyOne()) {
                        firstInjector.onClick(resourcePackArg);

                    } else {
                        openGenericScreen(resourcePackArg, injectors);
                    }
                }
            }
        }
    }

    private void openGenericScreen(ResourcePackArg resourcePackArg, ResourcePackEntryInjector[] injectors) {
        Minecraft.getInstance().setScreen(new RPButtonLibScreen(Minecraft.getInstance().screen, resourcePackArg, injectors));
    }

    public long getInjectorsCounter() {
        return injectorsCounter;
    }

    @ApiStatus.AvailableSince("1.0")
    public void addInjector(ResourcePackEntryInjector injector) {
        injectors.add(injector);
        injectorsCounter++;
    }
}
