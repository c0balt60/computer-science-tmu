package project_2;

import project_2.GamePanel;
import project_2.GameFrame;
import project_2.AnimatedCard;
import project_2.Card;

/**
 * Name: Andrii Naumenko <br/>
 * Student ID: 501385923
 *
 * <p>
 ******** <b>Project Description</b> ********
 *
 * <i>Describe in plain English the overall program/program in a paragraph or 2.
 * </i>
 *
 * Recently, I have been very busy with school, and I noticed my problem solving
 * skills dropped significantly.
 * My brain was only focused on computer-related problem solving, but I needed
 * to dilute more general problems so my brain wouldn't shutdown. Therefore, I
 * decided to create a blackjack simulator, to practice blackjack strategies,
 * and to practice OOP with Swing components in Java.
 *
 * This blackjack simulator features multiple betting options, currency
 * tracking,
 * and all the functionality of regular blackjack with the exception of
 * splitting (hit, stand, double are included).
 *
 *
 ******** <b>Swing Requirement</b> ********
 *
 * <i>Describe in 1 paragraph how your program satisfies the requirement that
 * there is at least 3 unique components. Be clear to identify in what
 * files and the lines number (just the starting line is fine) that the
 * components are defined on. </i>
 *
 * This program contains more than 3 unique components.
 * {@link AnimatedCard} Graphics wrapper for {@link Card}. Used in
 * {@link GamePanel} on lines 12 and 13 to track player and dealer cards.
 * GamePanel itself extends JPanel, and it handles drawing and scaling all
 * components.
 * {@link GameFrame} Draws the main window, and handles game actions and
 * component rendering
 *
 *
 ******** <b>2D Graphics Requirement</b> ********
 *
 * Describe in 1 paragraph how your program satisfies the requirement that
 * there is at least 1 JPanel used for drawing something. Be clear to
 * identify in what files and the line numbers that this panel is defined on.
 *
 * {@link GamePanel} is the primary JPanel that itself, handles drawing out the
 * playing table and positioning its child components.
 * When drawing out the player and dealer cards, GamePanel provides
 * {@link AnimatedCard} with its graphics2d, which AnimatedCard uses to draw
 * itself on the panel (necessary for card animations).
 * This is specifically seen in GamePanel line 502, card.draw(g, ...)
 * where g is the GamePanel's Graphics2D
 *
 *
 ******** <b>Event Listener Requirement</b> ********
 *
 * Describe in 1 paragraph how your program satisfies the requirement that
 * there is at least one ActionListener, and there is additionally at least
 * one MouseListener or ActionListener. Be clear to identify in what file
 * and the line numbers that these listeners are defined in.
 *
 * ActionListener was used to capture click on the buttons (hit, double, new
 * round) in GamePanel on line 133
 *
 * ComponentListener was used to capture screen resizing in ScaleProvider to
 * adjust the components based new screen width / height on line 144
 * (ScaleProvider).
 *
 * MouseListener was used in GameFrame to capture when users mouse hovered over
 * the GamePanel, to update the cursor of user in GameFrame on line 33
 *
 * </p>
 */
public class Project2Runner {

    public static void main(String[] args) {

    }
}
