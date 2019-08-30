package com.codeoftheweb.salvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepo;
    @Autowired
    private PlayerRepository playerRepo;
    @Autowired
    private GamePlayerRepository gamePlayerRepo;

    /*@RequestMapping("/players")
    public List<Player> getAllPlayers() {
        return playerRepo.findAll().stream().forEach();
    }*/

    /*@RequestMapping("/games")
    public List<Object> getAllGames() {
        List<Object> listOfGames = new ArrayList<>();
        listOfGames = gameRepo.findAll()
                .stream()
                .map(game -> getGames(game))
                .collect(Collectors.toList());
        return listOfGames;
    }*/

    @RequestMapping("/games")
    public Map<String, Object> getAllGames(Authentication auth){
        Map <String, Object> dto3 = new LinkedHashMap<>();
        if(auth != null){
        dto3.put("player", auth.getName());}
        else{dto3.put("player", "guest");}
        dto3.put("games", gameRepo.findAll()
            .stream()
            .map(game -> getGames(game))
            .collect(Collectors.toList()));
        return dto3;
    }

    private Map<String, Object> getGames(Game game){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", game.getId());
        dto.put("created", game.getDate());
        dto.put("gamePlayers", game.getGamePlayers()
                .stream()
                .map(gp -> getGamePlayers(gp))
                .collect(Collectors.toList()));
        return dto;
    }

    private Map<String, Object> getGameView(Game game, GamePlayer gamePlayer){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", game.getId());
        dto.put("created", game.getDate());
        dto.put("gamePlayers", game.getGamePlayers()
                .stream()
                .map(gp -> getGamePlayers(gp))
                .collect(Collectors.toList()));
        dto.put("ships", gamePlayer.getShips()
                .stream()
                .map(ship -> getShips(ship))
                .collect(Collectors.toList()));
        dto.put("salvos", gamePlayer.getGame().getGamePlayers()
                .stream()
                .map(gp -> getSalvos(gp))
                .collect(Collectors.toList()));
        return dto;
    }

    private Map<String, Object> getGamePlayers(GamePlayer gamePlayer){
        Map<String, Object> dto2 = new LinkedHashMap<>();
        dto2.put("id", gamePlayer.getId());
        dto2.put("player", getPlayer(gamePlayer.getPlayer()));
        return dto2;
    }

    private Map<String, Object> getPlayer(Player player){
        Map<String, Object> dto3 = new LinkedHashMap<>();
        dto3.put("id", player.getId());
        dto3.put("email", player.getUserName());
        dto3.put("firstName", player.getFirstName());
        dto3.put("lastName", player.getLastName());
        return dto3;
    }

    private Map<String, Object> getShips(Ship ship){
        Map<String, Object> dtoShips = new LinkedHashMap<>();
        dtoShips.put("type", ship.getType());
        dtoShips.put("location", ship.getListOfLocations());
        return dtoShips;
    }

    private Map<Long, Object> getSalvos (GamePlayer gp){
        Map<Long, Object> dtoSalvos = new LinkedHashMap<>();
        dtoSalvos.put(gp.getId(), getSalvosInfo(gp.getSalvos()));
        return dtoSalvos;
    }

    private Map<Integer, List> getSalvosInfo (Set<Salvo> salvos){
        Map<Integer, List> dtoSalvos2 = new LinkedHashMap<>();
        salvos.stream().forEach(salvo -> dtoSalvos2.put(salvo.getTurn(), salvo.getSalvoLocation()));
        return dtoSalvos2;
    }

    @RequestMapping("/game_view/{gamePlayerId}")
    public Map<String, Object> setupForm(@PathVariable Long gamePlayerId) {
        GamePlayer gamePlayer = gamePlayerRepo.getOne(gamePlayerId);
        Game game = gamePlayer.getGame();
        /* A la hora de hacer refactor del código se puede añadir directamente el getShips aquí!
        getData(gamePlayer.getGame()).put("ships", getShips());
        */
    return getGameView(game, gamePlayer);
    }

    @RequestMapping("/leader_board")
    public Map<String, Object> getLeaderBoard() {
        Map<String, Object> dto = new LinkedHashMap<>();
        playerRepo.findAll()
                .stream()
                .forEach(player -> dto.put(player.getUserName(), getScores(player.getScores())));
        return dto;
    }

    private Map<String, Double> getScores(Set<Score> scores){
        Map<String, Double> dto2 = new LinkedHashMap<>();
        dto2.put("points", 0.0);
        dto2.put("wins", 0.0);
        dto2.put("draws", 0.0);
        dto2.put("loses", 0.0);

        scores.stream().forEach(score -> {
            if(score.getScore() == 1.0){
                dto2.put("points", dto2.get("points") + 1.0);
                dto2.put("wins", dto2.get("wins") + 1.0);
            } else if (score.getScore() == 0.5) {
                dto2.put("points", dto2.get("points") + 0.5);
                dto2.put("draws", dto2.get("draws") + 1.0);
            } else {
                dto2.put("loses", dto2.get("loses") + 1.0);
            }
        });
        return dto2;
    }

    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<String> createPlayer(@RequestParam String userName, String password) {
        if (userName.isEmpty()) {
            return new ResponseEntity<>("No name given", HttpStatus.FORBIDDEN);
        }

        Player player = PlayerRepository.findByUserName(userName);
        if (player != null) {
            return new ResponseEntity<>("Name already used", HttpStatus.CONFLICT);
        }

        Player newPlayer = PlayerRepository.save(new Player(userName, password));
        return new ResponseEntity<>(makeMap("userName", newPlayer.getUserName()), HttpStatus.CREATED);
    }

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}