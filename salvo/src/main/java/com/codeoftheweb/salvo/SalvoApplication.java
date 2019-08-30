package com.codeoftheweb.salvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepo, GameRepository gameRepo, GamePlayerRepository gpRepo, ShipRepository shipRepo, SalvoRepository salvoRepo, ScoreRepository scoreRepo) {
		return (args) -> {
			Player p1 = new Player("j.bauer@ctu.gov", "Jack", " Bauer", passwordEncoder().encode("24"));
			Player p2 = new Player("c.obrian@ctu.gov", "Chloe", "O'Brian", passwordEncoder().encode("42"));
			Player p3 = new Player("kim_bauer@gmail.com", "Kim", "Bauer", passwordEncoder().encode("kb"));
			Player p4 = new Player("t.almeida@ctu.gov", "Tony", "Almeida", passwordEncoder().encode("mole"));

			playerRepo.save(p1);
			playerRepo.save(p2);
			playerRepo.save(p3);
			playerRepo.save(p4);

			Game g1 = new Game();
			Game g2 = new Game();
			Game g3 = new Game();
			Game g4 = new Game();
			Game g5 = new Game();
			Game g6 = new Game();
			Game g7 = new Game();
			Game g8 = new Game();

			gameRepo.save(g1);
			Date newDate2 = Date.from(g1.getDate().toInstant().plusSeconds(3600));
			g2.setDate(newDate2);
			gameRepo.save(g2);
			Date newDate3 = Date.from(g1.getDate().toInstant().plusSeconds(7200));
			g3.setDate(newDate3);
			gameRepo.save(g3);
			Date newDate4 = Date.from(g1.getDate().toInstant().plusSeconds(10800));
			g4.setDate(newDate4);
			gameRepo.save(g4);
			Date newDate5 = Date.from(g1.getDate().toInstant().plusSeconds(14400));
			g5.setDate(newDate5);
			gameRepo.save(g5);
			Date newDate6 = Date.from(g1.getDate().toInstant().plusSeconds(18000));
			g6.setDate(newDate6);
			gameRepo.save(g6);
			Date newDate7 = Date.from(g1.getDate().toInstant().plusSeconds(21600));
			g7.setDate(newDate7);
			gameRepo.save(g7);
			Date newDate8 = Date.from(g1.getDate().toInstant().plusSeconds(25200));
			g8.setDate(newDate8);
			gameRepo.save(g8);

			GamePlayer gp1 = new GamePlayer(g1, p1);
			GamePlayer gp2 = new GamePlayer(g1, p2);
			GamePlayer gp3 = new GamePlayer(g2, p1);
			GamePlayer gp4 = new GamePlayer(g2, p2);
			GamePlayer gp5 = new GamePlayer(g3, p2);
			GamePlayer gp6 = new GamePlayer(g3, p4);
			GamePlayer gp7 = new GamePlayer(g4, p2);
			GamePlayer gp8 = new GamePlayer(g4, p1);
			GamePlayer gp9 = new GamePlayer(g5, p4);
			GamePlayer gp10 = new GamePlayer(g5, p1);
			GamePlayer gp11 = new GamePlayer(g6, p3);
			GamePlayer gp12 = new GamePlayer(g7, p4);
			GamePlayer gp13 = new GamePlayer(g8, p3);
			GamePlayer gp14 = new GamePlayer(g8, p4);

			gpRepo.save(gp1);
			gpRepo.save(gp2);
			gpRepo.save(gp3);
			gpRepo.save(gp4);
			gpRepo.save(gp5);
			gpRepo.save(gp6);
			gpRepo.save(gp7);
			gpRepo.save(gp8);
			gpRepo.save(gp9);
			gpRepo.save(gp10);
			gpRepo.save(gp11);
			gpRepo.save(gp12);
			gpRepo.save(gp13);
			gpRepo.save(gp14);

			Ship s1 = new Ship("Destroyer", gp1, Arrays.asList("H2", "H3", "H4"));
			Ship s2 = new Ship("Submarine", gp1, Arrays.asList("E1", "F1", "G1"));
			Ship s3 = new Ship("Patrol Boat", gp1, Arrays.asList("B4", "B5"));
			Ship s4 = new Ship("Destroyer", gp2, Arrays.asList("B5", "C5", "D5"));
			Ship s5 = new Ship("Patrol Boat", gp2, Arrays.asList("F1", "F2"));
			Ship s6 = new Ship("Destroyer", gp3, Arrays.asList("B5", "C5", "D5"));
			Ship s7 = new Ship("Patrol Boat", gp3, Arrays.asList("C6", "C7"));
			Ship s8 = new Ship("Submarine", gp4, Arrays.asList("A2", "A3", "A4"));
			Ship s9 = new Ship("Patrol Boat", gp4, Arrays.asList("G6", "H6"));
			Ship s10 = new Ship("Destroyer", gp5, Arrays.asList("B5", "C5", "D5"));
			Ship s11 = new Ship("Patrol Boat", gp5, Arrays.asList("C6", "C7"));
			Ship s12 = new Ship("Submarine", gp6, Arrays.asList("A2", "A3", "A4"));
			Ship s13 = new Ship("Patrol Boat", gp6, Arrays.asList("G6", "H6"));
			Ship s14 = new Ship("Destroyer", gp7, Arrays.asList("B5", "C5", "D5"));
			Ship s15 = new Ship("Patrol Boat", gp7, Arrays.asList("C6", "C7"));
			Ship s16 = new Ship("Submarine", gp8, Arrays.asList("A2", "A3", "A4"));
			Ship s17 = new Ship("Patrol Boat", gp8, Arrays.asList("G6", "H6"));
			Ship s18 = new Ship("Destroyer", gp9, Arrays.asList("B5", "C5", "D5"));
			Ship s19 = new Ship("Patrol Boat", gp9, Arrays.asList("C6", "C7"));
			Ship s20 = new Ship("Submarine", gp10, Arrays.asList("A2", "A3", "A4"));
			Ship s21 = new Ship("Patrol Boat", gp10, Arrays.asList("G6", "H6"));
			Ship s22 = new Ship("Destroyer", gp11, Arrays.asList("B5", "C5", "D5"));
			Ship s23 = new Ship("Patrol Boat", gp11, Arrays.asList("C6", "C7"));
			Ship s24 = new Ship("Destroyer", gp13, Arrays.asList("B5", "C5", "D5"));
			Ship s25 = new Ship("Patrol Boat", gp13, Arrays.asList("C6", "C7"));
			Ship s26 = new Ship("Submarine", gp14, Arrays.asList("A2", "A3", "A4"));
			Ship s27 = new Ship("Patrol Boat", gp14, Arrays.asList("G6", "H6"));

			shipRepo.save(s1);
			shipRepo.save(s2);
			shipRepo.save(s3);
			shipRepo.save(s4);
			shipRepo.save(s5);
			shipRepo.save(s6);
			shipRepo.save(s7);
			shipRepo.save(s8);
			shipRepo.save(s9);
			shipRepo.save(s10);
			shipRepo.save(s11);
			shipRepo.save(s12);
			shipRepo.save(s13);
			shipRepo.save(s14);
			shipRepo.save(s15);
			shipRepo.save(s16);
			shipRepo.save(s17);
			shipRepo.save(s18);
			shipRepo.save(s19);
			shipRepo.save(s20);
			shipRepo.save(s21);
			shipRepo.save(s22);
			shipRepo.save(s23);
			shipRepo.save(s24);
			shipRepo.save(s25);
			shipRepo.save(s26);
			shipRepo.save(s27);

			Salvo salvo1 = new Salvo(gp1, 1, Arrays.asList("B5", "C5", "F1"));
			Salvo salvo2 = new Salvo(gp2, 1, Arrays.asList("B4", "B5", "B6"));
			Salvo salvo3 = new Salvo(gp1, 2, Arrays.asList("F2", "D5"));
			Salvo salvo4 = new Salvo(gp2, 2, Arrays.asList("E1", "H3", "A2"));
			Salvo salvo5 = new Salvo(gp3, 1, Arrays.asList("A2", "A4", "G6"));
			Salvo salvo6 = new Salvo(gp4, 1, Arrays.asList("B5", "D5", "C7"));
			Salvo salvo7 = new Salvo(gp3, 2, Arrays.asList("A3", "H6"));
			Salvo salvo8 = new Salvo(gp4, 2, Arrays.asList("C5", "C6"));
			Salvo salvo9 = new Salvo(gp5, 1, Arrays.asList("G6", "H6", "A4"));
			Salvo salvo10 = new Salvo(gp6, 1, Arrays.asList("H1", "H2", "H3"));
			Salvo salvo11 = new Salvo(gp5, 2, Arrays.asList("A2", "A3", "D8"));
			Salvo salvo12 = new Salvo(gp6, 2, Arrays.asList("E1", "F2", "G3"));
			Salvo salvo13 = new Salvo(gp7, 1, Arrays.asList("A3", "A4", "F7"));
			Salvo salvo14 = new Salvo(gp8, 1, Arrays.asList("B5", "C6", "H1"));
			Salvo salvo15 = new Salvo(gp7, 2, Arrays.asList("A2", "G6", "H6"));
			Salvo salvo16 = new Salvo(gp8, 2, Arrays.asList("C5", "C7", "D5"));
			Salvo salvo17 = new Salvo(gp9, 1, Arrays.asList("A1", "A2", "A3"));
			Salvo salvo18 = new Salvo(gp10, 1, Arrays.asList("B5", "B6", "C7"));
			Salvo salvo19 = new Salvo(gp9, 2, Arrays.asList("G6", "G7", "G8"));
			Salvo salvo20 = new Salvo(gp10, 2, Arrays.asList("C6", "D6", "E6"));
			Salvo salvo21 = new Salvo(gp10, 3, Arrays.asList("H1", "H8"));

			salvoRepo.save(salvo1);
			salvoRepo.save(salvo2);
			salvoRepo.save(salvo3);
			salvoRepo.save(salvo4);
			salvoRepo.save(salvo5);
			salvoRepo.save(salvo6);
			salvoRepo.save(salvo7);
			salvoRepo.save(salvo8);
			salvoRepo.save(salvo9);
			salvoRepo.save(salvo10);
			salvoRepo.save(salvo11);
			salvoRepo.save(salvo12);
			salvoRepo.save(salvo13);
			salvoRepo.save(salvo14);
			salvoRepo.save(salvo15);
			salvoRepo.save(salvo16);
			salvoRepo.save(salvo17);
			salvoRepo.save(salvo18);
			salvoRepo.save(salvo19);
			salvoRepo.save(salvo20);
			salvoRepo.save(salvo21);

			Score score1 = new Score(g1, p1, 1.0);
			Score score2 = new Score(g1, p2, 0.0);
			Score score3 = new Score(g2, p1, 0.5);
			Score score4 = new Score(g2, p2, 0.5);
			Score score5 = new Score(g3, p2, 1.0);
			Score score6 = new Score(g3, p4, 0.0);
			Score score7 = new Score(g4, p2, 0.5);
			Score score8 = new Score(g4, p1, 0.5);

			scoreRepo.save(score1);
			scoreRepo.save(score2);
			scoreRepo.save(score3);
			scoreRepo.save(score4);
			scoreRepo.save(score5);
			scoreRepo.save(score6);
			scoreRepo.save(score7);
			scoreRepo.save(score8);
		};
	}
}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
	@Autowired
	PlayerRepository playerRepository;
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inputName-> {
			Player player = playerRepository.findByUserName(inputName);
			if (player != null) {
				return new User(player.getUserName(), player.getPassword(),
						AuthorityUtils.createAuthorityList("USER"));
			} else {
				throw new UsernameNotFoundException("Unknown user: " + inputName);
			}
		});
	}
}

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/api/games").permitAll()
				.antMatchers("/api/scores").permitAll()
				.antMatchers("/api/leader_board").permitAll()
				.anyRequest().fullyAuthenticated();
		http.formLogin()
				.usernameParameter("name")
				.passwordParameter("pwd")
				.loginPage("/api/login");
		http.logout().logoutUrl("/api/logout");

		// turn off checking for CSRF tokens
		http.csrf().disable();

		// if user is not authenticated, just send an authentication failure response
		http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		// if login is successful, just clear the flags asking for authentication
		http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

		// if login fails, just send an authentication failure response
		http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		// if logout is successful, just send a success response
		http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
	}

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
	}
}
