package com.recursive_pineapple.matter_manipulator.common.building;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.google.gson.annotations.SerializedName;

import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.TMultiPart;

/**
 * Stores the data for a single multipart part in a serializable form.
 */
public class MultipartPart {

    /** The part type string used by MultiPartRegistry */
    @SerializedName("t")
    public String type;

    /** The part's NBT data (from TMultiPart.save()) */
    @SerializedName("nbt")
    public NBTTagCompound nbtData;

    /** The items this part drops (used for resource tracking) */
    @SerializedName("drops")
    public List<PortableItemStack> drops;

    public MultipartPart() {}

    /**
     * Creates a MultipartPart from a live TMultiPart.
     */
    public static MultipartPart fromPart(TMultiPart part) {
        MultipartPart mp = new MultipartPart();

        mp.type = part.getType();

        mp.nbtData = new NBTTagCompound();
        part.save(mp.nbtData);

        mp.drops = new ArrayList<>();
        for (ItemStack drop : part.getDrops()) {
            if (drop != null && drop.stackSize > 0) {
                mp.drops.add(PortableItemStack.withNBT(drop));
            }
        }

        return mp;
    }

    /**
     * Recreates the TMultiPart from stored data.
     *
     * @return The recreated part, or null if the part type is not registered.
     */
    public TMultiPart recreate() {
        TMultiPart part = MultiPartRegistry.createPart(type, false);

        if (part == null) return null;

        if (nbtData != null) {
            part.load(nbtData);
        }

        return part;
    }

    @Override
    public MultipartPart clone() {
        MultipartPart dup = new MultipartPart();
        dup.type = type;
        dup.nbtData = nbtData == null ? null : (NBTTagCompound) nbtData.copy();
        if (drops != null) {
            dup.drops = new ArrayList<>(drops.size());
            for (PortableItemStack drop : drops) {
                dup.drops.add(drop.clone());
            }
        }
        return dup;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((nbtData == null) ? 0 : nbtData.hashCode());
        result = prime * result + ((drops == null) ? 0 : drops.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MultipartPart other = (MultipartPart) obj;
        if (type == null) {
            if (other.type != null) return false;
        } else if (!type.equals(other.type)) return false;
        if (nbtData == null) {
            if (other.nbtData != null) return false;
        } else if (!nbtData.equals(other.nbtData)) return false;
        if (drops == null) {
            if (other.drops != null) return false;
        } else if (!drops.equals(other.drops)) return false;
        return true;
    }
}
