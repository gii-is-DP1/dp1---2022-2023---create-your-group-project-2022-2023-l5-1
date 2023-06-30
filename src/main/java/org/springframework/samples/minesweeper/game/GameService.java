package org.springframework.samples.minesweeper.game;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.util.Pair;
import org.springframework.samples.minesweeper.board.Board;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

    private GameRepository gameRepository;

    @Autowired
	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

    public Pair<List<String>, Boolean> initializeSquares(Board board){
        List<String> squares =new ArrayList<String>();
        Integer mines = board.getMinesNumber();
        Integer rows = board.getRows();
        Integer columns= board.getColumns();
        Boolean excessive = false;
    
        for(int i =0; i<rows; i++){
            for(int j=0; j<columns;j++){
                squares.add(i+"-"+j);
            }
        }
        Integer i= 1;
        //Colocación de minas
        List<String> mineList = new ArrayList<>();
        Integer maxMines = (int) Math.floor(0.9*columns*rows);
        if(mines >= maxMines) {
            mines = maxMines;
            excessive = true;
        }
        while(i<=mines){
            double random = Math.round(Math.random()*(squares.size()-1));
            int index = (int) random;
            String sq = squares.get(index);
            if(!mineList.contains(sq)){
                mineList.add(sq);
                i++;
            }
            
        }
        return Pair.of(mineList, excessive);
    }

    @Transactional
    public Game saveGame(Game game)  throws DataAccessException {
        return gameRepository.save(game);
    }

    public Game getGameById(Integer gameId) {
        return gameRepository.findGameById((gameId));
    }

    public Game getActiveGameByUsername(String username) {
        return gameRepository.findActiveGameByUsername((username));
    }

    public void deleteGame(Game game) {
        gameRepository.delete(game);
    }
    
}
