package com.alexogden.realms.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.alexogden.realms.entity.Entity;
import com.alexogden.realms.entity.mob.Mob;
import com.alexogden.realms.entity.mob.player.Player;
import com.alexogden.realms.entity.particle.Particle;
import com.alexogden.realms.entity.projectile.Projectile;
import com.alexogden.realms.graphics.Screen;
import com.alexogden.realms.level.tile.Tile;
import com.alexogden.realms.util.Vector2i;

public class Level {
	
	protected int WIDTH;
	protected int HEIGHT;
	protected int[] tilesInt;
	protected int[] tiles;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	
	private List<Player> players = new ArrayList<Player>();
	private List<Mob> mobs = new ArrayList<Mob>();
	
	public static Level SPAWN_LEVEL = new SpawnLevel("/tex/lvl/long.png");
	
	private Comparator<Node> nodeSorter = new Comparator<Node>() {
		public int compare(Node n0, Node n1) {
			if(n1.fCost < n0.fCost) return +1;
			if(n1.fCost > n0.fCost) return -1;
			return 0;
		}

	};
	
	public Level(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		tilesInt = new int[width*height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void loadLevel(String path) {
	}

	protected void generateLevel() {
	}
	
	public void update() {
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		
		for(int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		
		for(int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		
		for(int i = 0; i < players.size(); i++) {
			players.get(i).update();
		}
		
		for(int i = 0; i < mobs.size(); i++) {
			mobs.get(i).update();
		}
		remove();
	}
	
	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved())
				entities.remove(i);
		}

		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved())
				projectiles.remove(i);
		}

		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved())
				particles.remove(i);
		}
		
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(i).isRemoved())
				mobs.remove(i);
		}

	}
	
	public boolean tileCollision(double x, double y, double nx, double ny, int size) {
		size--;
		boolean solid = false;
		for(int c = 0; c < 4; c++) {
			//double xt = ((x + nx) + c % 2 * size - size/2 + 4)/16;
			//double yt = ((y + ny) + c / 2 * size - size/2 + 4)/16;
			double xt = ((x + nx) + c % 2 * (size - 1)) / 16;
			double yt = ((y + ny) + c / 2 * (size - 1)) / 16;
			if(getTile((int) xt, (int) yt).solid() == true) solid = true;
		}
		return solid;
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.WIDTH + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.HEIGHT + 16) >> 4;
		
		for(int y = y0; y < y1; y++) {
			for(int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		
		for(int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		
		for(int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
				
		for(int i = 0; i < mobs.size(); i++) {
			mobs.get(i).render(screen);
		}
		
		for(int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}
	}
	
	public List<Node> findPath(Vector2i start, Vector2i goal) {
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();

		Node current = new Node(start, null, 0, getDistance(start, goal));
		openList.add(current);

		while (openList.size() > 0) {
			Collections.sort(openList, nodeSorter);
			current = openList.get(0);

			if (current.tile.equals(goal)) {
				List<Node> path = new ArrayList<Node>();
				while (current.parent != null) {
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}

			openList.remove(current);
			closedList.remove(current);

			for (int i = 0; i < 9; i++) {
				if (i == 4)continue;

				int x = current.tile.getX();
				int y = current.tile.getY();

				int xi = (i % 3) - 1;
				int yi = (i / 3) - 1;

				Tile at = getTile(x + xi, y + yi);

				if (at == null)
					continue;
				if (at.solid())
					continue;

				Vector2i a = new Vector2i(x + xi, y + yi);
				double gCost = current.gCost + (getDistance(current.tile, a) == 1 ? 1 : 0.95);
				double hCost = getDistance(a, goal);
				Node node = new Node(a, current, gCost, hCost);

				if (vecInList(closedList, a) && gCost >= node.gCost)
					continue;
				if (!vecInList(openList, a) || gCost < node.gCost)
					openList.add(node);
			}
		}

		closedList.clear();

		return null;
	}
	
	private boolean vecInList(List<Node> list, Vector2i vec) {
		for(Node n : list) {
			if(n.tile.equals(vec)) return true;
		}
		return false;
	}
	
	private double getDistance(Vector2i vec1, Vector2i vec2) {
		double dx = vec1.getX() - vec2.getX();
		double dy = vec1.getY() - vec2.getY();
		return Math.sqrt((dx * dx) + (dy + dy));
	}
	
	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for(int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = (int) entity.getX();
			int y = (int) entity.getY();
			
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if(distance <= radius) result.add(entity);
		}
		return result;
	}
	
	public List<Player> getPlayers(Entity e, int radius) {
		List<Player> results = new ArrayList<Player>();
		int ex = (int)e.getX();
		int ey = (int)e.getY();
		for(int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int x = (int)player.getX();
			int y = (int)player.getY();

			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);

			double distance = Math.sqrt((dx * dx) + (dy * dy));

			if (distance <= radius) {
				results.add(player);
			}

		}

		return results;
	}
	
	public List<Mob> getMobsInRadius(Entity e, int radius) {
		List<Mob> results = new ArrayList<Mob>();
		int ex = (int)e.getX();
		int ey = (int)e.getY();
		for(int i = 0; i < players.size(); i++) {
			Mob mob = mobs.get(i);
			int x = (int)mob.getX();
			int y = (int)mob.getY();

			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);

			double distance = Math.sqrt((dx * dx) + (dy * dy));

			if (distance <= radius) {
				results.add(mob);
			}

		}
		return results;
	}
	
	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		}else if( e instanceof Mob) {
			mobs.add((Mob) e);
		} else {
			entities.add(e);
		}
	
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public int getEntitySize() {
		return (int)entities.size() + 1;
	}
	
	public int getProjectilesSize() {
		return (int)projectiles.size();
	}
	
	public int getParticlesSize() {
		return (int)particles.size();
	}
	
	public int getMobsSize() {
		return (int)mobs.size();
	}
	
	public int getPlayersSize() {
		return (int)players.size();
	}
	
	public List<Mob> getMobs() {
		return mobs;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayerAt(int index) {
		return players.get(index);
	}
	
	public Player getClientPlayer() {
		return players.get(0);
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public List<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) return Tile.voidTile;
		if(tiles[x + y * WIDTH] == Tile.GRASS) return Tile.grass;
		if(tiles[x + y * WIDTH] == Tile.GRASS_FLOWERS_DAISY) return Tile.grass_flowers_daisy;
		if(tiles[x + y * WIDTH] == Tile.GRASS_FLOWERS_ROSE) return Tile.grass_flowers_rose;
		if(tiles[x + y * WIDTH] == Tile.STONE_BRICK) return Tile.stone_brick;
		if(tiles[x + y * WIDTH] == Tile.STONE_BRICK_MOSSY) return Tile.stone_brick_mossy;
		if(tiles[x + y * WIDTH] == Tile.STONE_BRICK_CRACKED) return Tile.stone_brick_cracked;
		if(tiles[x + y * WIDTH] == Tile.STONE_SLAB) return Tile.stone_slab;
		if(tiles[x + y * WIDTH] == Tile.STONE) return Tile.stone;
		if(tiles[x + y * WIDTH] == Tile.SAND) return Tile.sand;
		if(tiles[x + y * WIDTH] == Tile.PLANKS_DARK) return Tile.planks_dark;
		if(tiles[x + y * WIDTH] == Tile.PLANKS_LIGHT) return Tile.planks_light;
		if(tiles[x + y * WIDTH] == Tile.PLANKS_OAK) return Tile.planks_oak;
		if(tiles[x + y * WIDTH] == Tile.PLANKS_ACIA) return Tile.planks_acia;
		if(tiles[x + y * WIDTH] == Tile.WOOD_FLOOR) return Tile.wood_floor;
		if(tiles[x + y * WIDTH] == Tile.SNOW) return Tile.snow;
		if(tiles[x + y * WIDTH] == Tile.DIRT) return Tile.dirt;
		if(tiles[x + y * WIDTH] == Tile.GRASS_FLOWERS_ORANGE) return Tile.grass_flowers_orange;
		if(tiles[x + y * WIDTH] == Tile.GRASS_FLOWERS_TULIP) return Tile.grass_flowers_tulip;
		
		return Tile.voidTile;
	}
}