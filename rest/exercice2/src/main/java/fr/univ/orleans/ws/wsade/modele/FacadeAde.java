package fr.univ.orleans.ws.wsade.modele;

import fr.univ.orleans.ws.wsade.exceptions.*;
import fr.univ.orleans.ws.wsade.modele.module.*;
import fr.univ.orleans.ws.wsade.modele.module.Module;

import java.util.Collection;

public interface FacadeAde {
    /**
     * Permet d'ajouter un enseignant à l'annuaire des enseignants
     * @param nom
     * @param prenom
     * @return l'id de l'enseignant créé
     */

    long ajouterUnEnseignant(String nom, String prenom);

    /**
     * Permet de créer un module avec un responsable et des volumes horaires définis
     * @param nomModule
     * @param descriptifModule
     * @param idProfesseur
     * @param volumeCM
     * @param volumeTD
     * @param volumeTP
     * @return
     * @throws ModuleDejaExistantException : Un module du même nom existe déjà
     * @throws ProfesseurInexistantException : Le supposé responsable n'est pas enregistré dans l'annuaire des professeurs
     */
    String creerUnModule(String nomModule, String descriptifModule, long idProfesseur, double volumeCM, double volumeTD, double volumeTP) throws ModuleDejaExistantException, ProfesseurInexistantException;

    /**
     * Permet de créer une nouvelle formation si elle n'existe pas encore et si le responsable est un enseignant connu
     * @param nomFormation
     * @param idResponsable
     * @return
     * @throws ProfesseurInexistantException : l'enseignant mentionné n'est pas connu
     * @throws FormationDejaExistanteException : une formation existe déjà avec ce nom
     */
    String creerUneFormation(String nomFormation, long idResponsable) throws ProfesseurInexistantException, FormationDejaExistanteException;

    Collection<Enseignant> getPersonnel();

    Collection<Formation> getFormations();

    Collection<Module> getModules();

    Module getModuleById(String identifiantModule) throws ModuleInexistantException;

    Enseignant getEnseignantById(long identifiant) throws ProfesseurInexistantException;

    Formation getFormationById(String idFormation) throws FormationInexistanteException;

    Seance getSeanceById(long idSeance) throws SeanceInexistanteException;

    /**
     * Permet de créer une séance dans un module donné, pour un enseignant donné, pour un type de séance donnée et pour une durée donnée
     * L'enseignant concerné tout comme le module sont mis à jour en conséquence avec cette nouvelle séance.
     * @param nomModule
     * @param idEnseignant
     * @param typeSeance
     * @param duree
     * @return l'identifiant de la séances créée
     * @throws ProfesseurInexistantException : le professeur n'a pas été trouvé
     * @throws ModuleInexistantException : le module n'est pas dans le catalogue
     * @throws FormationInexistanteException : la formation n'existe pas dans le catalogue
     */

    long creerSeance(String nomFormation, String nomModule, long idEnseignant, Seance.TypeSeance typeSeance, double duree) throws ProfesseurInexistantException, ModuleInexistantException, FormationInexistanteException, ModuleNonPresentDansFormationException;

    Collection<CM> getTousLesCMsDunModulePourUneFormation(String identifiantModule, String idFormation) throws ModuleInexistantException, FormationInexistanteException;

    Collection<TD> getTousLesTDsDunModulePourUneFormation(String identifiantModule, String idFormation) throws ModuleInexistantException, FormationInexistanteException;

    Collection<TP> getTousLesTPsPourUneFormation(String identifiantModule, String idFormation) throws ModuleInexistantException, FormationInexistanteException;

    Collection<Seance> getToutesLesInterventionsDunEnseignant(long idProfesseur) throws ProfesseurInexistantException;

    /**
     * Permet de supprimer une séance non seulement dans un module mais également pour l'enseignant concerné
     * La séance est enfin supprimée de l'historique.
     * @param idSeance
     * @throws SeanceInexistanteException : la séance demandée est inexistante
     */


    void supprimerSeance(long idSeance) throws SeanceInexistanteException;

    Collection<Enseignant> getResponsablesModules();

    Collection<Enseignant> getResponsablesFormations();

    /**
     * Permet de supprimer un enseignant pour lequel aucune responsabilité n'est enregistrée. De plus, toutes les séances
     * concernées par cet enseignant seront supprimées du SI.
     * @param idEnseignant
     * @throws ProfesseurInexistantException : L'enseignant n'existe pas dans le SI
     * @throws AEncoreUneResponsabiliteDeFormationException : L'enseignant concerné est responsable d'au moins une formation
     * @throws AEncoreUneResponsabiliteDeModuleException : l'enseignant concerné est responsable d'au moins un module
     */

    void supprimerEnseignant(long idEnseignant) throws ProfesseurInexistantException, AEncoreUneResponsabiliteDeFormationException, AEncoreUneResponsabiliteDeModuleException;

    /**
     * Permet de supprimer un module du SI. Toutes les séances rattachées d'une manière ou d'une autre à ce module sont
     * supprimées également. Les interventions des enseignants sont donc mises à jour en fonction
     * @param nomModule
     * @throws ModuleInexistantException : le module concerné n'existe pas.
     */

    void supprimerUnModule(String nomModule) throws ModuleInexistantException;

    /**
     * Permet de supprimer une formation du catalogue. Les modules restent présents dans les catalogues mais les
     * séances liées à cette formations doivent être supprimées
     * @param idFormation
     * @throws FormationInexistanteException
     */

    void supprimerUneFormation(String idFormation) throws FormationInexistanteException, SeanceInexistanteException;

    /**
     * Permet de changer le nombre de groupes de TD d'une formation
     * @param libelleFormation
     * @param nbGroupes
     * @throws FormationInexistanteException : la formation recherchée n'existe pas
     */

    void changerNombreDeGroupesDeTDs(String libelleFormation, int nbGroupes) throws FormationInexistanteException;

    /**
     * Permet de changer le nombre de groupes de TP d'une formation
     * @param libelleFormation
     * @param nbGroupes
     * @throws FormationInexistanteException : la formation recherchée n'existe pas
     */

    void changerNombreDeGroupesDeTPs(String libelleFormation, int nbGroupes) throws FormationInexistanteException;

    /**
     * Permet de modifier le responsable d'une formation
     * @param idFormation
     * @param idResponsable
     * @throws FormationInexistanteException : la formation concernée n'est pas dans le catalogue
     * @throws ProfesseurInexistantException : l'enseignant mentionné n'est pas connu du SI
     */

    void changerResponsableDuneFormation(String idFormation, long idResponsable) throws FormationInexistanteException, ProfesseurInexistantException;


    /**
     * Permet d'ajouter un module de catalogue de modules à une formation donnée dans le semestre donné
     * @param formationConcernee
     * @param module
     * @param semestre
     */
    void ajouterModuleAFormation(Formation formationConcernee, Module module, Formation.Semestre semestre);

    Module getModuleDeFormation(String idFormation, String idModule) throws ModuleInexistantException, FormationInexistanteException;

    double calculServiceEqTD(long idEnseignant) throws ProfesseurInexistantException;
}
