package com.brianmj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.Gdx.gl20;

public class MainScreen implements Screen {
    private ShapeRenderer shapeRenderer;

    MainScreen(){
        shapeRenderer = new ShapeRenderer();

    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        gl20.glClearColor(0f, 0.34f, 0.12f, 1.0f);
        gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Rectangle rect = retrieveLargestRectangel();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        punchCantorGasket(10f, 10f, rect.height, 6, shapeRenderer);

        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

        shapeRenderer = new ShapeRenderer();
    }

    private void punchCantorGasket(float xPos, float yPos, float squareSize, int recursionsLevel, ShapeRenderer sr){
        // draw the first square white
        sr.setColor(Color.WHITE);
        sr.rect(xPos, yPos, squareSize, squareSize);

        // Get one third of the size of the square to use for iteration through each row
        float oneThird = squareSize / 3;
        // now punch a hole in the center of the white square
        sr.setColor(Color.BLACK);
        sr.rect(xPos + oneThird, yPos + oneThird, oneThird, oneThird);

        float twoThrid = oneThird + oneThird;

        if(recursionsLevel > 0){
            // start with the lower left corner and work our way "counter clockwise"
            // row 1
            punchCantorGasket(xPos, yPos, oneThird, recursionsLevel - 1, sr);
            punchCantorGasket(xPos + oneThird, yPos, oneThird, recursionsLevel - 1, sr);
            punchCantorGasket(xPos + twoThrid, yPos, oneThird, recursionsLevel - 1, sr);

            // row 2, skip the center square
            punchCantorGasket(xPos, yPos + oneThird, oneThird, recursionsLevel - 1, sr);
            punchCantorGasket(xPos + twoThrid, yPos + oneThird, oneThird, recursionsLevel -1, sr);

            // row 3
            punchCantorGasket(xPos, yPos + twoThrid, oneThird, recursionsLevel - 1, sr);
            punchCantorGasket(xPos + oneThird, yPos + twoThrid, oneThird, recursionsLevel -1, sr);
            punchCantorGasket(xPos + twoThrid, yPos + twoThrid, oneThird, recursionsLevel - 1, sr);

        }
    }

    private Rectangle retrieveLargestRectangel() {

        // use the screen width and height as the basis of the rectangle
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        Rectangle rectangle = new Rectangle();

        if(width > height){
            rectangle.x = (width - height) / 2;
            rectangle.y = 0;
            rectangle.width = height - 20;
            rectangle.height = height - 20;
        }else {
            rectangle.x = 0;
            rectangle.y = (height - width) / 2;
            rectangle.width = width - 20;
            rectangle.height = width - 20;
        }

        return rectangle;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
