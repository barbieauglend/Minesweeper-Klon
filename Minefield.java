package minesweeper;

import java.util.Random;

public class Minefield
{
	private Random _random;
	private int[][] _values;
	private int _width;
	private int _height;
	private int _mineCount;
	private boolean _setUp;
	
	public Minefield(Level level, Random random)
	{
		_random = random;
		
		switch (level)
		{
			case Kinderleicht:
				Initialize(8, 8, 10);
				break;
			case Normal:
				Initialize(16, 16, 40);
				break;
			case Schwer:
				Initialize(30, 16, 99);
				break;
			case McGyver:
				Initialize(30, 16, 1);
		}
	}
	
	public Minefield(int width, int height, int mineCount)
	{
		Initialize(width, height, mineCount);
	}
	
	public int OnClick(int x, int y)
	{
		if (!_setUp)
		{
			SetUp(x, y);
		}
		return _values[x][y];
	}
	
	public int Width() { return _width; }
	public int Height() { return _height; }
	public int MineCount() { return _mineCount; }
	
	private void Initialize(int width, int height, int mineCount)
	{
		_setUp = false;
		_width = width;
		_height = height;
		_mineCount = mineCount;
		_values = new int[width][height];
	}
	
	private void SetUp(int initX, int initY)
	{
		_setUp = true;
		int minesPlaced = 0;
		while (minesPlaced < _mineCount)
		{
			int x = _random.nextInt(_width);
			int y = _random.nextInt(_height);
			
			if (x == initX && y == initY) continue;
			
			_values[x][y] = -1;
			minesPlaced++;
		}
		
		for (int x = 0; x < _width; x++)
		{
			for (int y = 0; y < _height; y++)
			{
				if (_values[x][y] == -1) continue;
				
				int minesAround = 0;
				
				if (x > 0 && y > 0 && _values[x - 1][y - 1] == -1) minesAround++;
				if (y > 0 && _values[x][y - 1] == -1) minesAround++;
				if (x < _width - 1 && y > 0 && _values[x + 1][y - 1] == -1) minesAround++;
				if (x > 0 && _values[x - 1][y] == -1) minesAround++;
				if (x < _width - 1 && _values[x + 1][y] == -1) minesAround++;
				if (x > 0 && y < _height - 1 && _values[x - 1][y + 1] == -1) minesAround++;
				if (y < _height - 1 && _values[x][y + 1] == -1) minesAround++;
				if (x < _width - 1 && y < _height - 1 && _values[x + 1][y + 1] == -1) minesAround++;
				
				_values[x][y] = minesAround;
			}
		}
	}

	boolean IsMine(int x, int y)
	{
		return _values[x][y] == -1;
	}
}
