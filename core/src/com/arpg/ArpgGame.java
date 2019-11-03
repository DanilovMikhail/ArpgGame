package com.arpg;

import com.arpg.screens.ScreenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ArpgGame extends Game {
	private SpriteBatch batch;
	// Идеи:
	// - Добавить выносливость, запас 100 ед., при ударе отнимается 20.
	// Если выносливости не хватает, то бить не получится. Выносливость должна
	// восполняться с какой-то скоростью. Когда герой стоит, выносливость
	// восстанавливается быстрее, чем когда он идет, а когда бежит, она еще
	// и тратится;
	// - Добавить получение Score героем за уничтожение монстров;
	// - Добавить команды монстров
	// - Добавить статистику
	// - Добавить эффекты
	// - Атака монстра может привести к злости соседних монстров
	// - Разнообразить монстров
	// - Таблица High Score

	// Домашнее задание:
	// - Придумать небольшую/большую доработку и реализовать ее (можно несколько)
	//1. Когда мобы бьют друг-друга шмот не выпадает
	//2. Ограничение сумки до 9 слотов. Добавление в лут сумок, которые увеличивают размер ячеек.
	//3. Изменен HP Bar

	@Override
	public void create() {
		batch = new SpriteBatch();
		ScreenManager.getInstance().init(this, batch);
		ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.GAME);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float dt = Gdx.graphics.getDeltaTime();
		getScreen().render(dt);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
