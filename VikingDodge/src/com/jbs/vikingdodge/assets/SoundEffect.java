package com.jbs.vikingdodge.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jbs.vikingdodge.Constants;

public class SoundEffect {
	
	private Sound sound;
	private String path;
	
	public SoundEffect(String path) {
		this(Gdx.audio.newSound(Gdx.files.internal(path)));
		this.path = path;
	}
	
	public SoundEffect(Sound sound) {
		this.sound = sound;
	}
	
	public void play() {
		play(Constants.MASTER_SOUND);
	}
	
	public void play(float volume) {
		if(Constants.MUTE) return;
		sound.play(volume);
	}
	
	public void loop() {
		loop(Constants.MASTER_SOUND);
	}
	
	public void loop(float volume) {
		if(Constants.MUTE) return;
		sound.loop(volume);
	}
	
	public void stop() {
		sound.stop(0);
	}
	
	public void stop(float time) {
		Timer.schedule(new Task() {
			@Override
			public void run() {
				sound.stop();
			}
		}, time);
	}
	
	public void dispose() {
		sound.dispose();
	}
	
	public Sound getSound() {
		return sound;
	}
	
	public String toString() {
		return path;
	}
}
