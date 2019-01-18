package com.didekinlib.json;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: pedro@didekin
 * Date: 2019-01-17
 * Time: 18:03
 */
public class MoshiUtilTest {

    static class BlackjackHand {

        private final Card hidden_card;
        private final List<Card> visible_cards;

        BlackjackHand(Card hidden_card, List<Card> visible_cards)
        {
            this.hidden_card = hidden_card;
            this.visible_cards = visible_cards;
        }
    }

    static class Card {

        private final char rank;
        private final Suit suit;

        Card(char rank, Suit suit)
        {
            this.rank = rank;
            this.suit = suit;
        }
    }

    enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES,;
    }

    @Test
    public void test_ToJsonStr()
    {
        Card card1 = new Card('1', Suit.SPADES);
        Card card2 = new Card('6', Suit.DIAMONDS);
        Card card3 = new Card('3', Suit.CLUBS);
        Card[] cards = new Card[]{card2, card1};
        BlackjackHand blackjackHand = new BlackjackHand(card3, Arrays.asList(cards));

        String card1ToJson = MoshiUtil.toJsonStr(card1);
        assertThat(card1ToJson, is("{\"rank\":\"1\",\"suit\":\"SPADES\"}"));

        String blakcJackToJson = MoshiUtil.toJsonStr(blackjackHand);
        assertThat(blakcJackToJson,
                is("{\"hidden_card\":{\"rank\":\"3\",\"suit\":\"CLUBS\"},\"visible_cards\":[{\"rank\":\"6\",\"suit\":\"DIAMONDS\"}," +
                        "{\"rank\":\"1\",\"suit\":\"SPADES\"}]}"));
    }
}
