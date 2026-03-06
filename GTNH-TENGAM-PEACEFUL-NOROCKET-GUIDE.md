# GTNH Tengam Guide: Peaceful + No Rockets Challenge (UHV)

**Analysis based on full source code decompilation of GTNH modpack.**

---

## TL;DR

- **Gaia Spirit**: The ROOT BLOCKER. Cannot fight Gaia Guardian on peaceful. "Life Essence" in Botania = "Gaia Spirit" (same item, internal vs display name). Every path to obtain it traces back to killing the Gaia Guardian.
- **Tengam**: ONLY obtainable via Blood Magic Meteor Ritual (Ion Thruster Jet focus). **BUT** the meteor costs **1,000,000,001 LP**, which requires the **Blood Orb of Armok** (Avaritia). The Armok Orb requires **Gaia Spirit** to craft. Therefore, **Tengam is ALSO gated behind Gaia Spirit**.
- **STRICT PACIFIST NOTE**: If you won't harm ANY living being (including self-sacrifice and the Gaia Guardian), both Gaia Spirit AND Tengam are **completely unobtainable**. No loot bags, no quest rewards (confirmed via quest DB analysis), no Blood Magic meteors (circular), no synthesis recipes exist.
- **Infinite Blood LP**: EEC + Well of Suffering works on peaceful (2,500 LP/sec). Self-sacrifice for bootstrapping. **But** max LP with T6 altar + Transcendent Orb is ~390M (with all rune slots as Runes of the Orb). Only the **Armok Orb** (requires Gaia Spirit) can reach 1B LP for the Tengam meteor.

---

## 1. ALL POSSIBILITIES TO GET TENGAM (Peaceful + No Rockets)

### Source 1: Blood Magic Meteor Ritual (Mark of the Falling Tower) -- YOUR ONLY OPTION (BUT GATED)

The **Ion Thruster Jet meteor** (`CheatyVeryLowQuantityRawTengam.json`) contains **Raw Tengam Ore as its filler block**. This is the ONLY pre-space method to obtain Tengam.

**CRITICAL LP COST ISSUE:**

The meteor config has `"cost": 1000000001` (1,000,000,001 LP). This is syphoned directly from the soul network per summon. Analysis of LP capacity limits:

| Orb | Base LP | Max with ~300 Runes of the Orb | Enough for 1B? |
|-----|---------|-------------------------------|----------------|
| Transcendent Blood Orb (T6) | 30,000,000 | ~390,000,000 | NO |
| Blood Orb of Armok (Avaritia) | 1,000,000,000 (auto-fill) | 1,000,000,000 | YES |

**The math:** `orbCapacityMultiplier = 1 + 0.04 × runeCount`. To reach 1B with a 30M orb: need `1B/30M ≈ 33.3` multiplier → need `808+ Runes of the Orb`. A T6 altar has ~200-300 total rune slots (across ALL rune types), so this is **physically impossible**.

**Therefore:** The Tengam meteor REQUIRES the **Blood Orb of Armok**, which requires **Gaia Spirit** to craft (Black Hole Talisman component). This means **Tengam is gated behind Gaia Spirit**.

**How to summon (once you have Armok Orb):**
1. Build the Mark of the Falling Tower ritual (17x17 footprint, 100 Ritual Stones: 32 air, 16 water, 20 fire, 20 earth, 12 dusk)
2. Activate with an **Awakened Activation Crystal** -- costs **100,000 LP** from soul network
3. Throw an **Ion Thruster Jet** (the focus item) onto the Master Ritual Stone
4. **1,000,000,001 LP** is syphoned from soul network (Armok Orb auto-refills instantly)
5. The meteor spawns above and crashes down, creating a sphere of ores with Tengam as filler

**Focus Item -- Ion Thruster Jet:**
- Assembly Line craft at UHV tier
- Focus item = `GalacticraftAmunRa:tile.machines2:1` -- this is the Ion Thruster Jet block
- Requires scanning a T1 Rocket Engine Jet in a Research Station

**Critical tip -- Use Orbis Terrae reagent:**
- Each Orbis Terrae (1,000 aspect ratio) increases the meteor radius by +2 blocks
- Since Tengam is the FILLER (not the main ore), more radius = dramatically more Tengam
- This is essential for efficiency

**Setup tips:**
- Place an unbreakable block ~35 blocks above the Master Ritual Stone
- Do this far away from your base (explosions damage through blocks)
- Soul Compactor (Blood Arsenal) can compact the ritual to 1 block for 318,171 LP
- With Armok Orb, you can spam meteors infinitely (LP auto-refills)

### Source 2: Seth (Tier 9 Planet) -- BLOCKED (No Rockets)

Seth is a moon in the Amun-Ra system. Primary source of Raw Tengam Ore veins (height 30-180, weight 80, size 32). Requires a Mothership or Space Elevator. **Not available in no-rocket runs.**

### Source 2b: "Cheaty" Tengam Meteor -- BLOCKED (Requires Space)

**File:** `GT-New-Horizons-Modpack/config/BloodMagic/meteors/CheatyVeryLowQuantityRawTengam.json`
- Contains `oreTengamRaw:1` as filler (very low quantity, hence the name)
- **Focus item:** `GalacticraftAmunRa:tile.machines2:1` -- requires Amun-Ra mod (space travel)
- **Cost:** 1,000,000,001 LP
- **BLOCKED** for both no-rocket AND LP cost reasons

### Source 2c: T9 Ores Meteor -- BLOCKED (Deep Endgame)

**File:** `GT-New-Horizons-Modpack/config/BloodMagic/meteors/T9Ores.json`
- Contains `oreTengamRaw:1` alongside Draconium Awakened, Neodymium, Samarium, Nether Star
- **Focus item:** `gregtech:gt.blockmachines:14009` = **Space Elevator Module Miner T3**
- **Cost:** 1,000,000,001 LP
- **BLOCKED** -- requires Space Elevator (deep endgame, far past where you need Tengam)

### Source 3: Space Miner MK-III Drones -- BLOCKED (Requires Tengam to build)

The Tengam asteroid exists in space mining, but the MK-III drones themselves require Tengam components. **Cannot be used as a bootstrap source.**

### Source 4: Crafting/Processing -- NO RECIPE EXISTS

There is NO crafting, chemical, or processing recipe that creates Tengam from other materials. It must be mined as ore.

### Tengam Processing Chain (once you have Raw Tengam Ore)

```
Raw Tengam Ore
    → Macerator → Raw Tengam Dust
        → Electromagnetic Separator → Purified Tengam Dust (+ 10% Neodymium Magnetic, 10% Samarium Magnetic)
            → Polarizer (UHV tier) → Attuned Tengam Dust
```

### The Grind Reality

Per the GTNH wiki: "You'll need to spawn this meteor **tens of thousands of times** in order to make the Space Miner MK III." Tengam ore is the filler block at very low weight (`oreTengamRaw:1`), so each meteor gives minimal Tengam. Using Orbis Terrae to maximize radius is absolutely critical. With the Armok Orb providing infinite 1B LP refills, the bottleneck becomes focus item crafting speed and chunk loading.

---

## 2. ALL POSSIBILITIES TO GET GAIA SPIRIT (Peaceful + No Rockets)

This is the hardest problem in this challenge combination. Here's every path analyzed:

### What is "Life Essence" / "Gaia Spirit"?

**IMPORTANT NAMING CLARIFICATION:** There are TWO things called "Life Essence" in GTNH:

1. **Botania's "Life Essence"** (internal name: `lifeEssence`, ore dict: `eternalLifeEssence`)
   - **Display name in English:** "Gaia Spirit"
   - **Item ID:** `Botania:manaResource` meta 5
   - **Source:** Dropped ONLY by killing the Gaia Guardian boss (8 per kill normal, 16 hard mode)
   - **THIS is the item that gates progression**

2. **Blood Magic's "Life Essence"** (fluid name: `lifeessence`)
   - A **fluid**, not an item. Completely separate from Botania's.
   - Used in Blood Magic's altar system (LP = Life Points)
   - Generated by self-sacrifice or mob sacrifice
   - **NOT related to Botania's Gaia Spirit at all**

**The GregTech material "GaiaSpirit"** (formula: `Gs`) has no component elements -- it cannot be synthesized from raw materials. It's registered as a "magic" material with no electrolyzer/centrifuge decomposition.

**Gaia Spirit Ingot** (Botania manaResource:14, ore dict: `gaiaIngot`) is crafted from:
- 4x Gaia Spirit (manaResource:5) + 1x Terrasteel Plate → 1x Gaia Spirit Ingot

The core problem is obtaining the **raw Gaia Spirit drop** (manaResource:5).

### Path A: Gaia Guardian Boss Fight -- BLOCKED (Peaceful)

**Source code confirms** (`EntityDoppleganger.java:151`):
```java
if(par3World.difficultySetting == EnumDifficulty.PEACEFUL) {
    player.addChatMessage("botaniamisc.peacefulNoob");
    return false;
}
```
The Gaia Guardian **cannot spawn on peaceful**. If somehow spawned, it **immediately despawns** (line 525-527). This is a hard block with no workaround in the source code.

Normal drops: 8-16x Life Essence per kill (hard mode: 16).

### Path B: Gaia Spirit Bees (GregTech) -- VIABLE BUT CIRCULAR DEPENDENCY

**Bee:** GAIASPIRIT (GTBeeDefinition.java:2711)
- **Parents:** NAQUADAH x TERRASTEEL
- **Mutation chance:** 1%
- **Mutation tier:** 3
- **Requirement:** `requireResource("frameGtGaiaSpirit")` -- needs a Gaia Spirit frame block adjacent to the apiary

**Production:** Gaia Spirit Combs (CombType.GAIASPIRIT, 15% base chance)

**LCR Processing (LuV tier):**
- Recipe 1: 4x Gaia Spirit Combs + 4x Pixie Dust + 1x **Dice of Fate** + 4L Elven Elementium → **4x Life Essence**
- Recipe 2: 4x Gaia Spirit Combs + 4x Pixie Dust + 1x **Dice of Fate** + 2L Terrasteel → **6x Life Essence**

**DOUBLE CIRCULAR DEPENDENCY:**
1. **Frame dependency:** Breeding the bee requires a `frameGtGaiaSpirit` block adjacent to the apiary → needs Gaia Spirit material → needs Life Essence → needs the boss
2. **Dice of Fate dependency:** The LCR recipes require a **Dice of Fate**, which ONLY drops from **Gaia Guardian II (hard mode)**. This means even if you somehow got the bee, you can't process the combs without having killed the hard-mode boss.

**CONCLUSION:** The bee path has TWO independent circular dependencies back to the Gaia Guardian. It is NOT a viable bootstrap path.

### Path C: Mixer Recipe -- NEEDS LIFE ESSENCE (Circular)

**Recipe** (MixerRecipes.java:917):
```
1x Terrasteel Dust + 4x Life Essence → 1x Gaia Spirit Dust (MV, 30 sec)
```
Still needs Life Essence as input.

### Path D: Forge Hammer -- NEEDS GAIA BLOCK (Circular)

**Recipe** (ForgeHammerRecipes.java:121):
```
1x Gaia Block + 1152L Prismatic Acid → 32x Life Essence (LuV)
```
But the Gaia Block is made from:
```
1x Bifrost Perm Block + 1296L Molten Gaia Spirit → 1x Gaia Block (IV, Fluid Solidifier)
```
Still circular.

### Path E0: ExtraUtilities Peaceful Table -- DISABLED IN GTNH

ExtraUtilities has a "Peaceful Table" that can produce mob drops without mobs. However, **GTNH explicitly disables it**:
- `GT-New-Horizons-Modpack/config/ExtraUtilities.cfg`: `PeacefultableEnabled=false`, `peacefulTableInAllDifficulties=false`
- Not a viable path.

### Path E: EEC (Extreme Entity Crusher) with ignorePeacefulCheck config -- POSSIBLE WORKAROUND

The EEC has a config option (`Config.MobHandler.ignorePeacefulCheck`, default: `false`) that, when set to `true`, allows processing hostile mob recipes on peaceful.

**Source code** (MTEExtremeEntityCrusher.java:632):
```java
if (!recipe.recipe.isPeacefulAllowed && world.difficultySetting == PEACEFUL && !Config.MobHandler.ignorePeacefulCheck)
    return "EEC_peaceful";
```

**However:** The Gaia Guardian explicitly rejects damage from non-real players (`isTruePlayer` check at EntityDoppleganger.java:390 rejects FakePlayer instances). The EEC uses fake players internally, so **the Gaia Guardian cannot be killed by the EEC** even with this config enabled.

### Path F: Quest Book Rewards -- CONFIRMED: NO GAIA SPIRIT REWARDS

**Quest database analyzed** (`GT-New-Horizons-Modpack/config/betterquesting/`). Every Flower Power quest referencing `manaResource` was checked:

- **"Round One... FIGHT!"** (quest #3231): REQUIRES 1x Gaia Spirit to complete. Reward: only CoinFlowerIII x10. **No Gaia Spirit rewarded.**
- **"Round Two... FIGHT!"** (quest #3241): REQUIRES 1x Gaia Spirit Ingot + Dice of Fate. Reward: only CoinFlowerIII x15. **No Gaia Spirit rewarded.**
- **"LAPUTA XX"** (endgame quest): Rewards 1x GaiaSpirit NUGGET (`gt.metaitem.01:9205`). But REQUIRES a Laputa Shard level 19, which needs 19x Gaia Spirit to craft (1 per upgrade level). **Circular -- costs 19 to gain 1/9th.**
- **All other Flower Power quests**: Reward coins, Terrasteel, Pixie Dust, Dragonstone, or mushrooms. **No Gaia Spirit.**

**CONCLUSION:** No quest provides Gaia Spirit without already having it.

### Path G: Loot Bags -- CONFIRMED: NO GAIA SPIRIT IN LOOT BAGS

Enhanced Loot Bags content checked in `ScriptEnhancedLootBags.java` -- **no Gaia Spirit in any loot tier**. Thaumcraft's rare bag loot table also checked (see `CLAUDE.md` for decompilation details) -- only vanilla Thaumcraft items, no Botania items.

### Path G2: Blood Magic BotGaia Meteor -- CIRCULAR DEPENDENCY

A Blood Magic meteor config exists specifically for Botania Gaia materials:

**File:** `GT-New-Horizons-Modpack/config/BloodMagic/meteors/BotGaia.json`
```json
{
  "ores": ["Botania:customBrick:4:90", ..., "dreamcraft:Gaia:0:2"],
  "radius": 7,
  "cost": 1000000001,
  "focusItem": "Botania:laputaShard:19"
}
```

The `dreamcraft:Gaia` block is a compressed Gaia Spirit block that breaks down to **32x Gaia Spirit** via Forge Hammer + Prismatic Acid. However:

1. **Focus item is Laputa Shard level 19** -- requires 19x Gaia Spirit to craft (1 per upgrade level from 0→19)
2. **Cost is 1,000,000,001 LP** -- exceeds even Archmage's Blood Orb capacity (10M base). Would need ~100+ Runes of the Orb
3. **Gaia Spirit blocks have weight 2 out of ~542** -- only ~0.4% of the meteor's blocks

**CONCLUSION:** Circular dependency (need Gaia Spirit to get Gaia Spirit) AND prohibitively expensive.

### Path H: Use `/gamerule doMobSpawning false` Instead of Peaceful -- BEST WORKAROUND

The Gaia Guardian checks `world.difficultySetting == EnumDifficulty.PEACEFUL`, NOT the `doMobSpawning` gamerule. This means:

1. Set difficulty to **Easy** (or Normal/Hard)
2. Run `/gamerule doMobSpawning false`
3. No hostile mobs will naturally spawn (same effect as peaceful for day-to-day play)
4. But the Gaia Guardian CAN be ritual-summoned because difficulty is not Peaceful
5. Kill it, get Life Essence, done

**This preserves the spirit of peaceful play** (no random hostile mobs) while allowing ritual-summoned bosses. If your challenge rules define "peaceful" as "no hostile mob spawns" rather than "difficulty locked to Peaceful", this is the cleanest solution.

**Tip:** Use the **Sword of the Cosmos** (Avaritia) for a one-hit kill -- "all it takes is one hit and the fight is over" (per GTNH wiki).

### Path I: Temporarily Switch Difficulty -- THE NUCLEAR OPTION

Minecraft allows changing difficulty at any time. You could:
1. Switch to Easy/Normal
2. Spawn and kill the Gaia Guardian (get 8-16 Life Essence)
3. Switch back to Peaceful
4. Use the Life Essence to make 1 Gaia Spirit Ingot → 1 frameGtGaiaSpirit
5. Bootstrap Gaia Spirit bees for infinite production

**This breaks the "peaceful challenge" rules.** But a SINGLE difficulty switch for ONE boss kill would unlock the entire Gaia Spirit chain permanently. However, the bee path also needs a Dice of Fate (Gaia Guardian II hard mode drop), so you'd need to kill the hard-mode guardian too.

**Note:** The GTNH Challenge Run wiki page explicitly states: "many challenge types may make the full pack progression impossible to complete." Peaceful + No Rockets is one of the hardest combinations.

### RECOMMENDED STRATEGY

**Option A: `/gamerule doMobSpawning false` approach (for players who will fight bosses)**
1. Switch difficulty to Easy, enable `/gamerule doMobSpawning false`
2. No hostile mobs spawn naturally (same as peaceful gameplay)
3. Summon Gaia Guardian (Normal mode) → get Gaia Spirit
4. Summon Gaia Guardian II (Hard mode) → get Dice of Fate
5. Use Sword of the Cosmos for instant kills
6. Make Gaia Spirit frame, breed Gaia Spirit bees
7. LCR: Gaia Spirit Combs + Dice of Fate → Gaia Spirit (infinite)

**Option B: Strict Pacifist (NO killing anything, NO harming any living being)**
- **Gaia Spirit is COMPLETELY UNOBTAINABLE.** Every path confirmed in source code:
  - Boss fight: Requires killing the Gaia Guardian (BLOCKED - pacifist)
  - Bees: Require Gaia Spirit frame + Dice of Fate (BLOCKED - circular + requires boss kill)
  - Mixer/Forge Hammer: Require existing Gaia Spirit as input (BLOCKED - circular)
  - Loot bags: Gaia Spirit not in any loot table (Enhanced Loot Bags + Thaumcraft bags confirmed)
  - Quest rewards: Exhaustive quest DB search confirms no Gaia Spirit rewards (LAPUTA XX gives 1 nugget but costs 19 Gaia Spirit — circular)
  - Blood Magic BotGaia meteor: Contains Gaia blocks but focus item requires 19 Gaia Spirit (circular) + costs 1B LP
  - GregTech synthesis: GaiaSpirit material has no component elements (formula `Gs`, no decomposition)
  - EEC: Cannot kill the Gaia Guardian (FakePlayer rejected by `isTruePlayer` check)
- **Conclusion:** Gaia Spirit and everything depending on it (Gaia Ingot, Gaia plates, etc.) is a HARD WALL for strict pacifist play.
- The GTNH Challenge Run wiki confirms: "many challenge types may make the full pack progression impossible to complete."

---

## 3. ALL POSSIBILITIES TO GET INFINITE BLOOD LP (Peaceful + No Rockets)

### STRICT PACIFIST WARNING

If you define pacifism as "no harming any living being" (including yourself and passive mobs), then:
- **Tier 1 (Self-Sacrifice): BLOCKED** -- the Sacrificial Knife damages the player
- **Tier 2 (Feathered Knife): BLOCKED** -- automated self-sacrifice, still harms the player
- **Tier 3 (Dagger of Sacrifice): BLOCKED** -- kills villagers and animals
- **Tier 4 (Well of Suffering): BLOCKED** -- damages all nearby living entities
- **Tier 5 (EEC Ritual Mode): WORKS** -- no actual entities harmed, it's a mechanical process
- **Tier 6 (World Accelerators): N/A** -- speeds up existing LP, doesn't generate it
- **Tier 7 (Convocation): BLOCKED** -- involves spawning and killing demons
- **Tier 8 (Blood Orb of Armok): BLOCKED** -- requires Gaia Spirit (see Section 2)

**THE BOOTSTRAP QUESTION FOR STRICT PACIFISTS:**

**If self-harm is acceptable** (you only object to harming OTHER living beings):
- Self-sacrifice with the Sacrificial Knife works. It reduces your HP to 10% -- it does NOT kill you.
- You regenerate naturally (food, potions, etc.) and repeat.
- This is enough to bootstrap all of Blood Magic including EEC Ritual Mode.
- Tiers 1, 2, and 5 are all available. Only Tiers 3, 4, and 7 (which harm other entities) are blocked.

**If ALL harm is unacceptable** (including to yourself):
- There is NO way to generate LP. Blood Magic is entirely predicated on sacrifice.
- Without LP, no Blood Altar recipes, no rituals, no meteor summoning.
- This is a second hard wall blocking Tengam (via meteor) AND everything else in Blood Magic.

### Tier 1: Self-Sacrifice (Early Game Bootstrap)

**Sacrificial Knife** -- No mobs needed, works immediately:
- Base: 100 LP per heart of damage
- Each **Rune of Self-Sacrifice** adds +20 LP per rune per heart

**Incense Altar Multiplier:**
- No path: +20%
- Wooden path: +60%
- Stone path: +120%
- Worn Stone path: +200%
- **Obsidian path: +300%** (best)

With Obsidian path Incense: the Sacrificial Knife becomes a Dagger that sacrifices 90% of your HP in one use.

**PACIFIST NOTE:** Self-sacrifice harms the PLAYER (you). If your pacifism only covers other beings, this is fine. If it includes self-harm, this is blocked too.

**Sustainability:** Use regeneration effects (Wand Focus: Mending, regen potions, food) between stabs.

**Rate:** Very slow. Expect 3-5+ hours of manual stabbing to accumulate enough LP for basic Blood Magic infrastructure (~1.5M LP for blank slates and rituals).

### Tier 2: Ritual of the Feathered Knife (Automated Self-Sacrifice)

A Tier 4 ritual that automates self-sacrifice:
- Damages all players within 31x31x41 area down to 30% HP
- Generates **100 LP per half-heart** into nearby Blood Altar
- Activation: 25,000 LP, refresh: 20 LP/operation
- Scales with **Runes of Self-Sacrifice**
- Works on peaceful (only affects players)
- Pair with **Ritual of Regeneration** for sustainability
- Need enough Self-Sacrifice runes for net-positive LP (100 LP generated vs 100 LP consumed per HP regen)

### Tier 3: Dagger of Sacrifice with Passive Mobs/Villagers

Works on peaceful since villagers and animals persist:
- **Animals:** 250 LP per kill
- **Villagers:** 2,000 LP per kill
- Scales with Runes of Sacrifice (+10% per rune)
- Automate with villager breeders for sustained LP

### Tier 4: Well of Suffering with Passive Mobs

The Well of Suffering ritual damages **all non-player living entities** within range:
- Base: 25 LP per point of damage
- Scales with Runes of Sacrifice
- Works with villagers and animals on peaceful
- Needs healing for mobs or continuous breeding
- Activation: 50,000 LP

### Tier 5: EEC + Well of Suffering (THE KEY BREAKTHROUGH -- EV tier)

The **Extreme Entity Crusher** in **Ritual Mode** is the primary answer for peaceful LP generation:

**How it works:**
1. Build the EEC multiblock (EV tier, 480 EU/t)
2. Enable Ritual Mode (screwdriver the controller or use GUI toggle)
3. Place Well of Suffering Master Ritual Stone **directly above** the EEC center
4. Blood Altar within 10 blocks vertically, 5 blocks horizontally
5. Activate with Weak Activation Crystal (50,000 LP)

**Fixed rates (mob type and sacrifice runes DON'T matter):**
- **+2,500 LP/sec** added to Blood Altar
- **-200 LP/sec** drained from Soul Network
- **Net: +2,300 LP/sec** (passive, automated)

**Power:** 480 EU/t (HV tier), locked

**This works on peaceful mode** because the EEC doesn't physically spawn mobs.

**Soul Compactor option:** Compact the Well of Suffering ritual into 1 block for 318,171 LP. Place 1-2 blocks above EEC center.

### Tier 6: World Accelerators (Speed Multiplier)

World Accelerators in **Tile Entity Mode** speed up Blood Altar processing:
- LV: 2x | MV: 4x | HV: 8x | **EV: 16x**
- Use Machine Controller Cover to toggle only when altar is active

### Tier 7: Convocation of the Damned (Demon Portal)

- Costs **15,000,000 LP** to activate (Archmage's Blood Orb + 13+ Runes of the Orb)
- Opens a Demon Portal spawning Demon Grunts
- They drop Life Shards (30%) and Soul Shards (30%)
- Can be captured and farmed via EEC

### Tier 8: Blood Orb of Armok (Avaritia -- TRUE Infinite LP)

**The ultimate solution.** When in your inventory, it fills your soul network to **1,000,000,000 LP** instantly whenever it drops below that.

**Recipe (Extreme Crafting Table 9x9):**
```
   ---aia---
   --ababa--
   --jacaj--
   -dababad-
   ddeafagdd
   -dddhddd-
   ---ddd---
   ---------
   ---------
```
- a = Infinity Plate
- b = Eldritch Orb (Forbidden Magic)
- c = Blood Infused Diamond Block (Blood Arsenal)
- d = Cosmic Neutronium Plate
- e = Focus of Time (Tainted Magic)
- f = Infinity Catalyst (Avaritia Resource:5)
- g = Focus of Eldritch (Tainted Magic)
- h = Neutronium Large Plate (Tinker's)
- i = Black Hole Talisman (Botania)
- j = Sigil of Elemental Affinity (Blood Magic)

**WARNING:** The Black Hole Talisman requires **Life Essence** (Gaia Spirit) to craft. This means the Armok Orb is also blocked by the Gaia Spirit problem described in Section 2.

### Blood Orb Capacity Progression

| Tier | Orb | Base Capacity |
|------|-----|---------------|
| 1 | Weak Blood Orb | 5,000 LP |
| 2 | Apprentice Blood Orb | 25,000 LP |
| 3 | Magician's Blood Orb | 150,000 LP |
| 4 | Master Blood Orb | 1,000,000 LP |
| 5 | Archmage's Blood Orb | 10,000,000 LP |
| 6 | Transcendent Blood Orb | 30,000,000 LP |
| 7 | Blood Orb of Armok | 1,000,000,000 LP |

Each Rune of the Orb adds **+4% of base capacity**.

### LP Cost Reference for Meteor Ritual

- Ritual activation: **100,000 LP** (Awakened Activation Crystal)
- Per-meteor LP cost is defined in `GT-New-Horizons-Modpack/config/BloodMagic/meteors/*.json`
- Range: 123,456 LP (Rainbow Glass) to 1,000,000,001 LP (Tengam/BotGaia/T9Ores)
- **Ion Thruster Jet (Tengam) meteor: 1,000,000,001 LP** -- requires Blood Orb of Armok
- Most normal meteors: 300K-50M LP (achievable with Transcendent Blood Orb + runes)

---

## 4. RECOMMENDED OVERALL STRATEGY

### Phase 1: Blood Magic Bootstrap (EV tier)
1. Start with manual self-sacrifice (Sacrificial Knife + Incense Altar with Obsidian path)
2. Build up to Tier 4 Blood Altar
3. Build EEC multiblock

### Phase 2: Automated LP Generation
4. Set up EEC + Well of Suffering in Ritual Mode
5. Net +2,300 LP/sec passively
6. Build up to Tier 5-6 Blood Altar with Runes of the Orb

### Phase 3: Gaia Spirit (THE ROOT BOTTLENECK -- gates BOTH itself AND Tengam)
7. Switch to Easy + `/gamerule doMobSpawning false` (recommended)
8. Kill Gaia Guardian (normal) for Life Essence + Gaia Guardian II (hard) for Dice of Fate
9. Use Sword of the Cosmos for instant kills
10. Make Gaia Spirit frame, breed Gaia Spirit bees (NAQUADAH x TERRASTEEL, 1% chance)
11. LCR: Gaia Spirit Combs + Dice of Fate → Life Essence (infinite chain established)

### Phase 4: Armok Orb (Required for Tengam meteor)
12. Craft Black Hole Talisman (requires Gaia Spirit)
13. Craft Blood Orb of Armok (Extreme Crafting Table 9x9, requires Black Hole Talisman + many endgame components)
14. Armok Orb auto-fills soul network to 1,000,000,000 LP

### Phase 5: Tengam Meteor Farming (requires Armok Orb)
15. Craft Ion Thruster Jet (Assembly Line, UHV tier)
16. Set up Mark of the Falling Tower ritual
17. Use Orbis Terrae reagent for maximum radius
18. Farm meteors -- each costs 1B LP but Armok Orb auto-refills
19. Process: Raw Tengam → EM Separator → Purified Tengam → Polarizer (UHV) → Attuned Tengam

### Phase 6: Endgame
20. With Gaia Spirit bees + Tengam from meteors, craft the Tengam Electromagnet and progress

---

## 5. KEY SOURCE CODE REFERENCES

| File | Content |
|------|---------|
| `Botania/.../EntityDoppleganger.java:151` | Peaceful mode blocks Gaia Guardian spawn |
| `GT5-Unofficial/.../GTBeeDefinition.java:2711` | Gaia Spirit bee definition |
| `GT5-Unofficial/.../ItemComb.java:1348` | Gaia Spirit comb → Life Essence LCR recipes |
| `GT5-Unofficial/.../MaterialsInit.java` | TengamRaw/Purified/Attuned material definitions |
| `GT5-Unofficial/.../OreMixes.java:1002` | Tengam ore generation (Seth dimension only) |
| `GT5-Unofficial/.../MTEExtremeEntityCrusher.java:632` | EEC peaceful check + config bypass |
| `BloodMagic/.../RitualEffectSummonMeteor.java` | Meteor ritual mechanics |
| `BloodMagic/.../MeteorRegistry.java` | Meteor config loading (JSON + MineTweaker) |
| `BloodMagic/.../RitualEffectWellOfSuffering.java` | Well of Suffering ritual |
| `NewHorizonsCoreMod/.../MixerRecipes.java:917` | Terrasteel + Life Essence → Gaia Spirit |
| `NewHorizonsCoreMod/.../ForgeHammerRecipes.java:121` | Gaia Block → 32x Life Essence |
| `Avaritia/.../ItemOrbArmok.java` | Blood Orb of Armok (infinite LP) |
| `Avaritia/.../Bloody.java` | Armok Orb registration |
| `NewHorizonsCoreMod/.../ScriptAvaritia.java:413` | GTNH Armok Orb recipe (Extreme Crafting) |
| `GT-New-Horizons-Modpack/config/BloodMagic/meteors/BotGaia.json` | Gaia Spirit meteor (circular, 1B LP) |
| `GT-New-Horizons-Modpack/config/BloodMagic/meteors/CheatyVeryLowQuantityRawTengam.json` | Alt Tengam meteor (needs space) |
| `GT-New-Horizons-Modpack/config/BloodMagic/meteors/T9Ores.json` | T9 meteor w/ Tengam (Space Elevator focus) |
| `GT-New-Horizons-Modpack/config/betterquesting/.../LAPUTAXX-*.json` | LAPUTA XX quest (1 GS nugget, costs 19 GS) |
| `GT-New-Horizons-Modpack/config/ExtraUtilities.cfg:30` | Peaceful Table DISABLED in GTNH |
| `GT-New-Horizons-Modpack/config/Botania.cfg:120` | Built-in recipes disabled (expert mode) |
| `NewHorizonsCoreMod/.../BlockList.java:63` | dreamcraft:Gaia block definition |
| `NewHorizonsCoreMod/.../FluidSolidifierRecipes.java:122` | Gaia Block crafting (Bifrost + molten GS) |
| `GT5-Unofficial/.../MaterialsInit.java:14104` | GaiaSpirit material (ID 205, no components) |
