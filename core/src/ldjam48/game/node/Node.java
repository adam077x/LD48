package ldjam48.game.node;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Node> nodes;
    public String name;
    public Node parent;

    public Vector2 position;
    public Vector2 scale;
    public float rotation;

    public Node(String name, Vector2 position, Vector2 scale, float rotation) {
        this.name = name;
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;

        nodes = new ArrayList<Node>();
    }

    public Node(String name) {
        this.name = name;
        this.position = new Vector2();
        this.scale = new Vector2();
        this.rotation = 0;

        nodes = new ArrayList<Node>();
    }

    public void update(SpriteBatch batch, float delta) {
        for(Node n : nodes) {
            n.position.x += position.x;
            n.position.y += position.y;

            n.update(batch, delta);

            n.position.x -= position.x;
            n.position.y -= position.y;
        }
    }

    public <T extends Node> T getNode(Class<T> node) {
        for(Node n : nodes) {
            if(node.isAssignableFrom(node.getClass())) {
                try {
                    n.parent = this;
                    return node.cast(n);
                }
                catch (ClassCastException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public Node addNode(Node n) {
        nodes.add(n);
        return n;
    }
}
