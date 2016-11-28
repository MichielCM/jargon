<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
	<article>
		<label>
			<input type="checkbox" />
			<xsl:for-each select="//s">
				<section>
					<xsl:for-each select="w">
						<xsl:variable name="wID">
							<xsl:value-of select="@xml:id"/>
						</xsl:variable>
						<span>
							<xsl:attribute name="class">
								<xsl:if test="sense[@class='keyword-extraction']/feat[@class='keyword']">keyword</xsl:if>
								<xsl:if test="sense[@class='polarity']/feat[@class='negative']"> negative</xsl:if>
							</xsl:attribute>
							<span>
								<xsl:value-of select="t" />
							</span>
							<xsl:for-each select="sense[@class='map']">
								<cite>
									<xsl:attribute name="title">
										Zekerheid: <xsl:value-of select="@confidence" />
									</xsl:attribute>
									<abbr><xsl:value-of select="@synset" /></abbr>
									<dfn><xsl:value-of select="feat[@subset='match']/@class" /></dfn>
								</cite>
							</xsl:for-each>
						</span>
					</xsl:for-each>
				</section>
			</xsl:for-each>
		</label>
	</article>
</xsl:template>
	
</xsl:stylesheet>