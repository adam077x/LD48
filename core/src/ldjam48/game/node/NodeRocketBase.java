package ldjam48.game.node;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import ldjam48.game.TextureManager;
import ldjam48.game.gui.base.RocketMenu;
import ldjam48.game.node.drill.NodePlayer;
import ldjam48.game.screens.MainEndScene;
import ldjam48.game.screens.MainGameScreen;

public class NodeRocketBase extends NodeSprite {
    public NodeRocketBase() {
        super("Node Rocket Base", TextureManager.rocket_ramp2, 256, 256);
    }

    public static boolean flyAway = false;

    @Override
    public void update(SpriteBatch batch, float delta) {
        if(flyAway) {
            position.y += delta * 250;
            NodePlayer player = (NodePlayer) MainGameScreen.scene.findNode("Player");
            player.camera.position.y = position.y;
            System.out.println(player.stop);
            player.stop = true;
            player.camera.update();

            if(position.y >= 1000) {
                MainGameScreen.getInstance().getGame().setScreen(new MainEndScene());
            }
        }

        super.update(batch, delta);
    }

    public Rectangle getRectangle() {
        return new Rectangle(position.x, position.y, width, height);
    }
}
