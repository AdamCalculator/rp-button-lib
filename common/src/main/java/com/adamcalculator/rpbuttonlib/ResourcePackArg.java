package com.adamcalculator.rpbuttonlib;

import net.minecraft.client.gui.screens.packs.TransferableSelectionList;
import org.jetbrains.annotations.ApiStatus;

/**
 * May difference in different minecraft versions
 */
@ApiStatus.AvailableSince("1.0")
public class ResourcePackArg {
    private final TransferableSelectionList.PackEntry packEntry;
    private final TransferableSelectionList list;


    // INTERNAL
    private ResourcePackArg(TransferableSelectionList.PackEntry packEntry, TransferableSelectionList list) {
        this.packEntry = packEntry;
        this.list = list;
    }

    // INTERNAL ONLY
    @ApiStatus.Internal
    public static ResourcePackArg createInternal(Object resourcePackEntryMixin, Object parent) {
        return new ResourcePackArg((TransferableSelectionList.PackEntry) resourcePackEntryMixin, (TransferableSelectionList) parent);
    }


    public TransferableSelectionList.PackEntry getPackEntry() {
        return packEntry;
    }

    public TransferableSelectionList getList() {
        return list;
    }
}
