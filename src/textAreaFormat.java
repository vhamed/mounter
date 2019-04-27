facture.setText("\tExpert Informatique");
facture.append("\n\n***********************************");
if(!this.cpuDescription.toLowerCase().equals("vide")){
    facture.append("\n\nCpu: "+cpuDescription);
}
if(!this.cmDescription.toLowerCase().equals("vide")){
    facture.append("\n\nCarte Mere: "+cmDescription);
}
if(!this.ramDescription.toLowerCase().equals("vide")){
    facture.append("\n\nRam: "+ramDescription);
}
if(!this.disqueDescription.toLowerCase().equals("vide")){
    facture.append("\n\nDisque: "+disqueDescription);
}
if(!this.disque2Description.toLowerCase().equals("vide")){
    facture.append("\n\nDisque2: "+disque2Description);
}
if(!this.cgDescription.toLowerCase().equals("vide")){
    facture.append("\n\nCarte Graphique: "+cgDescription);
}
if(!this.alimentationDescription.toLowerCase().equals("vide")){
    facture.append("\n\nAlimentation: "+alimentationDescription);
}
if(!this.ecranDescription.toLowerCase().equals("vide")){
    facture.append("\n\nEcran: "+alimentationDescription);
}
if(!this.dvdDescription.toLowerCase().equals("vide")){
    facture.append("\n\nAvec Graveur DVD");
}
facture.append("\n\n***********************************");
facture.append("\n\n\tTotal: "+String.valueOf(total)+" DA");
