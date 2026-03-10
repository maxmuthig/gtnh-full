package com.recursive_pineapple.matter_manipulator.common.persist;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.recursive_pineapple.matter_manipulator.common.building.AEAnalysisResult;
import com.recursive_pineapple.matter_manipulator.common.building.ArchitectureCraftAnalysisResult;
import com.recursive_pineapple.matter_manipulator.common.building.GTAnalysisResult;
import com.recursive_pineapple.matter_manipulator.common.building.ITileAnalysisIntegration;
import com.recursive_pineapple.matter_manipulator.common.building.MultipartAnalysisResult;

/**
 * GSON adapter that handles polymorphic serialization of ITileAnalysisIntegration.
 * Adds a "_type" discriminator field to distinguish concrete types.
 */
public class TileAnalysisJsonAdapter
    implements JsonSerializer<ITileAnalysisIntegration>, JsonDeserializer<ITileAnalysisIntegration> {

    @Override
    public JsonElement serialize(ITileAnalysisIntegration src, Type typeOfSrc, JsonSerializationContext context) {
        JsonElement element = context.serialize(src, src.getClass());

        if (element.isJsonObject()) {
            element.getAsJsonObject()
                .addProperty("_type", getTypeName(src));
        }

        return element;
    }

    @Override
    public ITileAnalysisIntegration deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        if (!json.isJsonObject()) return null;

        JsonObject obj = json.getAsJsonObject();

        if (!obj.has("_type")) return null;

        String type = obj.get("_type")
            .getAsString();

        return switch (type) {
            case "gt" -> context.deserialize(json, GTAnalysisResult.class);
            case "ae" -> context.deserialize(json, AEAnalysisResult.class);
            case "arch" -> context.deserialize(json, ArchitectureCraftAnalysisResult.class);
            case "mp" -> context.deserialize(json, MultipartAnalysisResult.class);
            default -> null;
        };
    }

    private String getTypeName(ITileAnalysisIntegration src) {
        if (src instanceof GTAnalysisResult) return "gt";
        if (src instanceof AEAnalysisResult) return "ae";
        if (src instanceof ArchitectureCraftAnalysisResult) return "arch";
        if (src instanceof MultipartAnalysisResult) return "mp";
        return "unknown";
    }
}
