<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0">
    <xsl:output encoding="UTF-8" indent="yes" method="xml" standalone="no" omit-xml-declaration="no"/>
    <xsl:param name="code"/>
    <xsl:param name="date" select="','"/>
    <xsl:template match="Etudiants">
        <fo:root language="FR"  >
            <fo:layout-master-set  >
                <fo:simple-page-master master-name="business-card"
                                       page-height="29.7cm"
                                       page-width="21cm"
                >
                    <fo:region-body  margin-top="55px"/>
                    <fo:region-before extent="2in"  />
                    <fo:region-after extent="5in" />
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="business-card" color="black">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:table table-layout="fixed" width="100%" font-size="10pt"  margin-top="10px" >
                        <fo:table-column column-width="proportional-column-width(30)"/>
                        <fo:table-column column-width="proportional-column-width(45)"/>

                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell text-align="center" display-align="center" >
                                    <fo:block font-size="100%" margin-top="3px" >
                                        ROYAUME DE MAROC
                                    </fo:block>
                                    <fo:block font-size="100%" margin-top="3px">
                                        Universite Abdelmalek Essaadi
                                    </fo:block>
                                    <fo:block font-size="100%" margin-top="3px">
                                        Ecole Nationale des Sciences
                                    </fo:block>
                                    <fo:block font-size="100%" margin-top="3px">
                                        Appliquées Tanger
                                    </fo:block>
                                    <fo:block font-size="100%" margin-top="20px"  text-decoration="underline">
                                        Service des Affaires Estudiantines
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="right" display-align="center" padding-right="2mm">
                                    <fo:block >
                                        <fo:external-graphic src="pics/school.png" content-width="scale-to-fit"
                                                             content-height="30%"
                                                             width="30%"
                                                             scaling="uniform"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    <fo:block text-align="center" display-align="center"   margin-top="10px" margin-bottom="20px" >
                        <fo:block font-size="20px" margin-top="30px"  margin-bottom="20px" text-decoration="underline" font-weight="bold">
                           ATTESTATION DE SCOLARITE
                        </fo:block>
                        Le directeur de l'Ecole Nationale des sciences appliquées Ensa Tanger atteste que l'élève ingénieur:
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body" border-collapse="collapse" margin-left="40px" reference-orientation="0" >
<!--text-align="center" display-align="center"-->
                    <fo:table table-layout="fixed" width="100%"  margin-top="160px" >
                        <fo:table-column column-width="proportional-column-width(40)"/>
                        <fo:table-column column-width="proportional-column-width(30)"/>
                        <fo:table-column column-width="proportional-column-width(30)"/>
                        <fo:table-body >
                            <xsl:for-each select="//*[@Apogee=$code]">
                                <fo:table-row>
                                    <fo:table-cell   margin-left="25px">
                                        <fo:block margin-bottom="10px">
                                            Mensieur/Madame :
                                        </fo:block>
                                        <fo:block  margin-bottom="10px">
                                            Numéro de la carte d'identité nationale:
                                        </fo:block>
                                        <fo:block  margin-bottom="10px">
                                            Code national de l'etudiant(e):
                                        </fo:block>
                                        <fo:block  margin-bottom="10px">
                                            Né le
                                        </fo:block >
                                        <fo:block  margin-bottom="10px">
                                            à
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell   margin-left="25px">
                                        <fo:block margin-bottom="10px">
                                            <fo:inline font-weight="bold" padding-right="5pt">
                                                <xsl:value-of select="Nom"/>
                                            </fo:inline>
                                            <fo:inline font-weight="bold">
                                                <xsl:value-of select="Prenom"/>
                                            </fo:inline>
                                        </fo:block>
                                        <fo:block  margin-bottom="10px">
                                            <xsl:value-of select="Code_identite"/>
                                        </fo:block>
                                        <fo:block  margin-bottom="10px">
                                             <xsl:value-of select="@Apogee"/>
                                        </fo:block>
                                        <fo:block  margin-bottom="10px">
                                            <xsl:value-of select="Date_Naissance"/>
                                        </fo:block>
                                        <fo:block  margin-bottom="10px">
                                            <xsl:value-of select="Origine"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                    <fo:block  margin-left="25px" margin-top="20px">
                        Poursuit ses études à l'École Nationale des Sciences
                        Appliquées à Tanger pour l'année Universitaire 2022/2023
                    </fo:block>
                    <fo:table table-layout="fixed" width="100%" >
                    <fo:table-column column-width="proportional-column-width(10)"/>
                    <fo:table-column column-width="proportional-column-width(40)"/>
                        <fo:table-column column-width="proportional-column-width(50)"/>

                        <fo:table-body>
                    <fo:table-row>
                    <fo:table-cell >
                        <fo:block  margin-bottom="10px" margin-top="20px">
                            Diplôme
                        </fo:block >
                        <fo:block  margin-bottom="10px">
                            Filière
                        </fo:block>
                        <fo:block  margin-bottom="10px">
                            Année
                        </fo:block>
                    </fo:table-cell>
                        <fo:table-cell >
                            <fo:block  margin-bottom="10px" margin-top="20px">
                                Genie Informatique
                            </fo:block >
                            <fo:block  margin-bottom="10px">
                                Genie Informatique
                            </fo:block>
                            <fo:block  margin-bottom="10px">
                                2eme Année genie Informatique
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell><fo:block></fo:block></fo:table-cell>
                                <fo:table-cell><fo:block></fo:block></fo:table-cell>
                                <fo:table-cell>
                                    <fo:block margin-top="20px">
                                        Fait à TANGER le <xsl:value-of select="$date"/>
                                    </fo:block>
                                    <fo:block margin-top="20px">
                                        Le directeur
                                    </fo:block>
                                </fo:table-cell>

                            </fo:table-row>
                    </fo:table-body>
                    </fo:table>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>