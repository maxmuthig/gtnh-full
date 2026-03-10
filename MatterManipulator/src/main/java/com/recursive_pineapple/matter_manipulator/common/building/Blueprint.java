package com.recursive_pineapple.matter_manipulator.common.building;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import org.joml.Vector3i;

/**
 * A saved blueprint - a reusable snapshot of a copied region.
 * Stores blocks relative to origin (0,0,0) for position-independent pasting.
 */
public class Blueprint {

    @SerializedName("d")
    public Vector3i deltas;

    @SerializedName("b")
    public List<BlueprintEntry> blocks;

    public Blueprint() {}

    public Blueprint(Vector3i deltas, List<PendingBlock> pendingBlocks) {
        this.deltas = new Vector3i(deltas);
        this.blocks = new ArrayList<>(pendingBlocks.size());

        for (PendingBlock block : pendingBlocks) {
            this.blocks.add(BlueprintEntry.fromPendingBlock(block));
        }
    }

    /**
     * Converts the stored blueprint entries back into PendingBlocks for building.
     *
     * @param worldId The world ID to use for the blocks
     */
    public List<PendingBlock> toPendingBlocks(int worldId) {
        List<PendingBlock> result = new ArrayList<>(blocks.size());

        for (BlueprintEntry entry : blocks) {
            result.add(entry.toPendingBlock(worldId));
        }

        return result;
    }

    /**
     * A single block entry in a blueprint.
     * Uses concrete types instead of ITileAnalysisIntegration to avoid GSON polymorphism issues.
     */
    public static class BlueprintEntry {

        @SerializedName("x")
        public int x;
        @SerializedName("y")
        public int y;
        @SerializedName("z")
        public int z;
        @SerializedName("s")
        public BlockSpec spec;
        @SerializedName("gt")
        public GTAnalysisResult gt;
        @SerializedName("ae")
        public AEAnalysisResult ae;
        @SerializedName("arch")
        public ArchitectureCraftAnalysisResult arch;
        @SerializedName("mp")
        public MultipartAnalysisResult mp;
        @SerializedName("inv")
        public InventoryAnalysis inventory;
        @SerializedName("ro")
        public int renderOrder;
        @SerializedName("bo")
        public int buildOrder;

        public static BlueprintEntry fromPendingBlock(PendingBlock block) {
            BlueprintEntry entry = new BlueprintEntry();
            entry.x = block.x;
            entry.y = block.y;
            entry.z = block.z;
            // spec is ImmutableBlockSpec, but BlockSpec implements it and is GSON-friendly
            if (block.spec instanceof BlockSpec bs) {
                entry.spec = bs;
            } else {
                // Create a new BlockSpec from the immutable one
                entry.spec = new BlockSpec();
                entry.spec.setObject(block.spec.toStack(1));
            }
            if (block.gt instanceof GTAnalysisResult g) entry.gt = g;
            if (block.ae instanceof AEAnalysisResult a) entry.ae = a;
            if (block.arch instanceof ArchitectureCraftAnalysisResult ar) entry.arch = ar;
            if (block.mp instanceof MultipartAnalysisResult m) entry.mp = m;
            entry.inventory = block.inventory;
            entry.renderOrder = block.renderOrder;
            entry.buildOrder = block.buildOrder;
            return entry;
        }

        public PendingBlock toPendingBlock(int worldId) {
            PendingBlock block = spec.instantiate(worldId, x, y, z);
            block.gt = gt;
            block.ae = ae;
            block.arch = arch;
            block.mp = mp;
            block.inventory = inventory;
            block.renderOrder = renderOrder;
            block.buildOrder = buildOrder;
            return block;
        }
    }
}
