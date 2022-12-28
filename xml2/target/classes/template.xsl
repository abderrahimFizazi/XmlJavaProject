<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0">
    <xsl:output encoding="UTF-8" indent="yes" method="xml" standalone="no" omit-xml-declaration="no"/>
    <xsl:template match="Etudiants">
        <fo:root language="FR"  >
            <fo:layout-master-set>
                <fo:simple-page-master master-name="business-card"
                                       page-height="4in"
                                       page-width="7in"
                                       margin-top="0.1in"
                                       margin-left="0.1in"
                                       margin-right="0.1in">
                    <fo:region-body  margin-top="55px" />
<!--                   You can add Background here :  background-image="url('pics/background.png')"-->
                    <fo:region-before extent="2in"/>
                    <fo:region-after extent="5in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="business-card" color="rgb(31, 117, 254)">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:table table-layout="fixed" width="100%" font-size="10pt" >
                        <fo:table-column column-width="proportional-column-width(20)"/>
                        <fo:table-column column-width="proportional-column-width(45)"/>
                        <fo:table-column column-width="proportional-column-width(20)"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell text-align="left" display-align="center" padding-left="2mm">
                                    <fo:block >
                                        <fo:external-graphic src="pics/univ.png" content-width="scale-to-fit"
                                                             content-height="60%"
                                                             width="60%"
                                                             scaling="uniform"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center" display-align="center" >
                                    <fo:block font-size="100%">
                                            Universite Abdelmalek Essaadi
                                    </fo:block>
                                    <fo:block font-size="100%">
                                    Ecole Nationale des Sciences Appliquées
                                    </fo:block>
                                    <fo:block font-size="100%">
                                    Tanger
                                    </fo:block>
                                    <fo:block border-top="2pt solid orange"  margin-top="10px" margin-bottom="10px"/>

                                </fo:table-cell>
                                <fo:table-cell text-align="right" display-align="center" padding-right="2mm">
                                    <fo:block >
                                        <fo:external-graphic src="pics/school.png" content-width="scale-to-fit"
                                                             content-height="60%"
                                                             width="60%"
                                                             scaling="uniform"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body" border-collapse="collapse" reference-orientation="0" >
                    <fo:block text-align="center" display-align="center" font-weight="bold" font-size="15px"  margin-top="10px" margin-bottom="20px" >CARTE D'ETUDIANT</fo:block>
                    <fo:table table-layout="fixed" width="100%" font-size="10pt"  text-align="center" display-align="center" space-after="5mm">
                        <fo:table-column column-width="proportional-column-width(30)"/>
                        <fo:table-column column-width="proportional-column-width(50)"/>
                        <fo:table-column column-width="proportional-column-width(30)"/>
                        <fo:table-body font-size="95%">

                            <xsl:for-each select="Etudiant">
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block text-align="center">
                                            <fo:external-graphic src="{src}"
                                                                 content-width="scale-to-fit"
                                                                 content-height="90%"
                                                                 width="90%"
                                                                 scaling="uniform"

                                            />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left"  font-size="12px"  margin-left="25px">
                                        <fo:block margin-bottom="10px">
                                            <xsl:value-of select="Nom"/>
                                        </fo:block>
                                        <fo:block  margin-bottom="10px">
                                            <xsl:value-of select="Prenom"/>
                                        </fo:block>
                                        <fo:block  margin-bottom="10px">
                                            <xsl:value-of select="code"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            <fo:instream-foreign-object>
                                                <xsl:variable name="barcode-cfg">
                                                    <barcode>
                                                        <code128>
                                                            <height>8mm</height>
                                                        </code128>
                                                    </barcode>
                                                </xsl:variable>
                                                <xsl:copy-of select="barcode:generate($barcode-cfg, '0123456789')"/>
                                            </fo:instream-foreign-object>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                    <fo:block id="end-of-document" text-align="center" display-align="center" margin-top="40px">
                        Premiere Inscription : 2018 / 2019
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>