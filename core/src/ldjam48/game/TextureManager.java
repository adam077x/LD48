package ldjam48.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.xml.soap.Text;

public class TextureManager {
    public static final Texture img = new Texture("badlogic.jpg");
    public static final Texture dirt = new Texture("dirt.png");
    public static final Texture grass = new Texture("grass.png");
    public static final Texture stone = new Texture("stone.png");
    public static final Texture player = new Texture("player.png");
    public static final Texture bedrock = new Texture("bedrock.png");
    public static final Texture iron = new Texture("iron.png");
    public static final Texture coal_ore =  new Texture("coal_ore.png");
    public static final Texture playerBase = new Texture("player_base.png");
    public static final Texture hills = new Texture("mountains.png");
    public static final Texture sandstone = new Texture("sandstone.png");
    public static final Texture sand = new Texture("Sand.png");
    public static final Texture sandIron = new Texture("Sandiron.png");
    public static final Texture sandCoal = new Texture("Sandcoal.png");
    public static final Texture gold = new Texture("gold.png");
    public static final Texture silver = new Texture("silver.png");
    public static final Texture button = new Texture("button.png");
    public static final Texture buttonFlash = new Texture("button_flash.png");
    public static final Texture blackstone = new Texture("blackstone.png");
    public static final Texture lavaBlackstone = new Texture("lavablackstone.png");
    public static final Texture diamondOre = new Texture("diamond_ore.png");
    public static final Texture diamond = new Texture("diamond.png");
    public static final Texture mechanicalPart = new Texture("mechanical_part.png");
    public static final Texture magmaBlock = new Texture("magma_block.png");
    public static final Texture magmaIngot = new Texture("magma_ingot.png");
    public static final Texture metalParts = new Texture("metal_parts.png");
    public static final Texture rocket_ramp = new Texture("ramap_01.png");
    public static final Texture rocket_ramp2 = new Texture("raketa_rampa01.png");
    public static final Texture rocket = new Texture("raketa01.png");
    public static final Texture trashbin = new Texture("trash_bin.png");
    public static final Texture rocket_ramp3 = new Texture("raketa_rampa02.png");

    public static final Texture drills = new Texture("drills.png");

    public static TextureRegion[][] drillRegion = TextureRegion.split(drills, 16, 16);


    //Buildings
    public static final Texture storage = new Texture("storage.png");
    public static final Texture garage = new Texture("garage.png");
    public static final Texture factory = new Texture("factory.png");
    public static final Texture furnace = new Texture("furnace.png");

    //Player
    public static final Texture player_rotated = new Texture("player_rotated.png");



    //Inventory
    public static final Texture inventory = new Texture("inventory_tile.png");
    public static final Texture backgroundGui = new Texture("background_gui.png");
    public static final Texture arrow = new Texture("arrow_pointing.png");
    public static final Texture blank = new Texture("blank.png");

    //Blank

    public static final Texture blankWhite = new Texture("blank_white.png");

    //Items
    public static final Texture coal = new Texture("coal.png");
    public static final Texture iron_ingot = new Texture("iron_ingot.png");
    public static final Texture silver_ingot = new Texture("silver_ingot.png");
    public static final Texture gold_ingot = new Texture("gold_ingot.png");

}
