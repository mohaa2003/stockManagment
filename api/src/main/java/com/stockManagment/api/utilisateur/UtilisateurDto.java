package com.stockManagment.api.utilisateur;

import com.stockManagment.api.entreprise.EntrepriseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto {
    private Integer id;
    private Boolean isDeleted;
    private String username;
    private String password;
    private Roles role;
    private EntrepriseDto entreprise;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setId(utilisateur.getId());
        utilisateurDto.setIsDeleted(utilisateur.getIsDeleted());
        utilisateurDto.setUsername(utilisateur.getUsername());
        utilisateurDto.setPassword(utilisateur.getPassword());
        utilisateurDto.setRole(utilisateur.getRole());
        utilisateurDto.setEntreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()));
        return utilisateurDto;
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto) {
        if (utilisateurDto == null) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setIsDeleted(utilisateurDto.isDeleted);
        utilisateur.setUsername(utilisateurDto.getUsername());
        utilisateur.setPassword(utilisateurDto.getPassword());
        utilisateur.setRole(utilisateurDto.getRole());
        utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntreprise()));
        // Pas de conversion pour nombreUtilisateur car il n'est pas présent dans l'entité Utilisateur
        return utilisateur;
    }
}
