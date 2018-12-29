package File;

public interface ShowBeing {
    char reportIdentifier();
    int reportHealth();
    int reportMaxHealth();
    void modifyIdentifier(char identifier);
    void modifyHealth(int health);
    void modifyMaxHealth(int maxhealth);
}
