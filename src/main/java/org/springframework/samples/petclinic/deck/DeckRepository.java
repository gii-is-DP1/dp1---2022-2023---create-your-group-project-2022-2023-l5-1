package org.springframework.samples.petclinic.deck;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DeckRepository extends CrudRepository<Deck, Integer> {
    
    @Query("SELECT D FROM Deck D INNER JOIN D.cards C WHERE C.id = :cardId")
	Collection<Deck> findByCardId(@Param("cardId") int cardId) throws DataAccessException;

    @Query(value = "SELECT D.card_id FROM decks_cards D WHERE D.deck_id = :deckId", nativeQuery = true)
	Collection<Integer> findCardsInDeck(int deckId) throws DataAccessException;

    @Query(value = "SELECT * FROM DECK WHERE ID LIKE ?1", nativeQuery = true)
    Deck findDeckById(@Param("id") int id);
}



