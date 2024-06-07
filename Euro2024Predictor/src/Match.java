
class Match {
    private Team team1;
    private Team team2;

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public Team predictWinner() {
        return team1.getStrength() > team2.getStrength() ? team1 : team2;
    }
}