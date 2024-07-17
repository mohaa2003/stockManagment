package com.stockManagment.api.dette;

import com.stockManagment.api.versement.Versement;
import com.stockManagment.api.versement.VersementAutre;
import jakarta.persistence.OneToMany;

import java.util.List;

public class DetteAutre extends Dette{

    @OneToMany(mappedBy = "dette")
    private List<VersementAutre> versementAutreListList;
}
