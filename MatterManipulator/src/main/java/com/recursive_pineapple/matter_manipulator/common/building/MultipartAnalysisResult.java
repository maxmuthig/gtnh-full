package com.recursive_pineapple.matter_manipulator.common.building;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.google.gson.annotations.SerializedName;
import com.recursive_pineapple.matter_manipulator.MMMod;
import com.recursive_pineapple.matter_manipulator.common.building.BlockAnalyzer.IBlockApplyContext;
import com.recursive_pineapple.matter_manipulator.common.items.manipulator.Transform;
import com.recursive_pineapple.matter_manipulator.common.utils.BigItemStack;

import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultipartHelper;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;

public class MultipartAnalysisResult implements ITileAnalysisIntegration {

    @SerializedName("parts")
    public List<MultipartPart> parts;

    public static MultipartAnalysisResult analyze(TileEntity tile) {
        if (!(tile instanceof TileMultipart multipart)) return null;

        var partList = multipart.jPartList();

        if (partList == null || partList.isEmpty()) return null;

        MultipartAnalysisResult result = new MultipartAnalysisResult();
        result.parts = new ArrayList<>();

        for (TMultiPart part : partList) {
            if (part != null) {
                result.parts.add(MultipartPart.fromPart(part));
            }
        }

        return result.parts.isEmpty() ? null : result;
    }

    @Override
    public boolean apply(IBlockApplyContext ctx) {
        if (parts == null || parts.isEmpty()) return true;

        World world = ctx.getWorld();
        int x = ctx.getX();
        int y = ctx.getY();
        int z = ctx.getZ();

        BlockCoord coord = new BlockCoord(x, y, z);

        for (MultipartPart mp : parts) {
            TMultiPart part = mp.recreate();

            if (part == null) {
                ctx.warn("Could not recreate multipart of type: " + mp.type);
                continue;
            }

            try {
                if (TileMultipart.canPlacePart(world, coord, part)) {
                    TileMultipart.addPart(world, coord, part);
                } else {
                    ctx.warn("Could not place multipart of type: " + mp.type + " (space occupied)");
                }
            } catch (Exception e) {
                MMMod.LOG.error("Error placing multipart", e);
                ctx.error("Error placing multipart of type: " + mp.type + ": " + e.getMessage());
                return false;
            }
        }

        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileMultipart tile) {
            tile.markDirty();
            tile.markRender();
            MultipartHelper.sendDescPacket(world, tile);
        }

        return true;
    }

    @Override
    public boolean getRequiredItemsForExistingBlock(IBlockApplyContext context) {
        return getRequiredItemsForNewBlock(context);
    }

    @Override
    public boolean getRequiredItemsForNewBlock(IBlockApplyContext context) {
        if (parts == null) return true;

        for (MultipartPart mp : parts) {
            if (mp.drops != null) {
                List<BigItemStack> items = new ArrayList<>();
                for (PortableItemStack drop : mp.drops) {
                    ItemStack stack = drop.toStack();
                    if (stack != null) {
                        items.add(BigItemStack.create(stack));
                    }
                }
                if (!items.isEmpty()) {
                    context.tryConsumeItems(items, 0);
                }
            }
        }

        return true;
    }

    @Override
    public void getItemTag(NBTTagCompound tag) {
        // FMP blocks don't use item tags in the traditional sense
    }

    @Override
    public void getItemDetails(List<String> details) {
        if (parts != null) {
            details.add(parts.size() + " part(s)");
        }
    }

    @Override
    public void transform(Transform transform) {
        // Multipart rotation is complex (would need to rotate slots/shapes).
        // Parts are placed as-is for now. Rotation support can be added later.
    }

    @Override
    public void migrate() {

    }

    @Override
    public MultipartAnalysisResult clone() {
        MultipartAnalysisResult dup = new MultipartAnalysisResult();

        if (parts != null) {
            dup.parts = new ArrayList<>(parts.size());
            for (MultipartPart part : parts) {
                dup.parts.add(part.clone());
            }
        }

        return dup;
    }

    @Override
    public int hashCode() {
        return parts == null ? 0 : parts.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MultipartAnalysisResult other = (MultipartAnalysisResult) obj;
        if (parts == null) {
            if (other.parts != null) return false;
        } else if (!parts.equals(other.parts)) return false;
        return true;
    }
}
