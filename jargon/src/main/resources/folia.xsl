<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
	<div class="foliaViewer">
		<xsl:for-each select="FoLiA/text/p">
			<div class="p">
				<span class="token">
					<xsl:value-of select="t"/>
				</span>
				<xsl:for-each select="s">
					<div class="s">
						<span>Dependency Tree</span>
						<span>Lemma</span>
						<span>Morphemes</span>
						<span>Part of Speech</span>
						<span>Attributes</span>
						<span>Sense</span>
						<xsl:for-each select="w">
							<xsl:variable name="wID">
								<xsl:value-of select="@xml:id"/>
							</xsl:variable>
							<div class="w">
								<xsl:attribute name="id">
									<xsl:value-of select="@xml:id"/>
								</xsl:attribute>
								<span>
									<xsl:attribute name="class">
										<xsl:if test="sense[@class='keyword-extraction']/feat[@class='keyword']">keyword</xsl:if>
										<xsl:if test="sense[@class='polarity']/feat[@class='negative']"> negative</xsl:if>
									</xsl:attribute>
									<xsl:attribute name="style">padding-left:<xsl:choose>
										<xsl:when test="//dependencies/dependency/dep/wref[@id=$wID]">
											<xsl:call-template name="markDependants">
												<xsl:with-param name="hdID" select="//dependencies/dependency/dep/wref[@id=$wID]/../../hd/wref/@id"/>
												<xsl:with-param name="counter" select="1"/>
											</xsl:call-template>
										</xsl:when>
										<xsl:otherwise>0</xsl:otherwise>
									</xsl:choose>px</xsl:attribute>
									<xsl:attribute name="data-dependency-class">
										<xsl:choose>
											<xsl:when test="//dependencies/dependency/dep/wref[@id=$wID]/../../@class">
												<xsl:value-of select="//dependencies/dependency/dep/wref[@id=$wID]/../../@class"/>
											</xsl:when>
											<xsl:otherwise>root</xsl:otherwise>
										</xsl:choose>
									</xsl:attribute>
									<xsl:value-of select="t"/>
								</span>
								<span>
									<xsl:value-of select="lemma/@class"/>
								</span>
								<span>
									<xsl:for-each select="morphology/morpheme">
										[<xsl:value-of select="t"/>]
									</xsl:for-each>
								</span>
								<span>
									<xsl:attribute name="title">
										<xsl:for-each select="pos/feat">
											<xsl:value-of select="@subset"/>: <xsl:value-of select="@class"/>
											<xsl:if test="not(position() = last())">, </xsl:if>
										</xsl:for-each>
									</xsl:attribute>
									<xsl:value-of select="pos/@head"/>
								</span>
								<span class="custom">
									<xsl:for-each select="../dependencies/dependency/feat[@subset='relation'][@class='attribute']/../feat[@subset='inheritedBy']">
										<xsl:if test="@class = $wID">
											<xsl:value-of select="../dep/wref/@t"/>
										</xsl:if>
									</xsl:for-each>
								</span>
								<span class="custom">
									<xsl:for-each select="sense/feat">
										<xsl:value-of select="@subset"/>: <xsl:value-of select="@class"/>
										<xsl:if test="not(position() = last())">, </xsl:if>
									</xsl:for-each>
								</span>
							</div>
						</xsl:for-each>
					</div>
				</xsl:for-each>
			</div>
		</xsl:for-each>
	</div>
</xsl:template>

<xsl:template name="markDependants">
	<xsl:param name="hdID"/>
	<xsl:param name="counter"/>
	
	<xsl:choose>
		<xsl:when test="//dependencies/dependency/dep/wref[@id=$hdID]">
			<xsl:call-template name="markDependants">
				<xsl:with-param name="hdID" select="//dependencies/dependency/dep/wref[@id=$hdID]/../../hd/wref/@id"/>
				<xsl:with-param name="counter" select="$counter+1"/>
			</xsl:call-template>
		</xsl:when>
		<xsl:otherwise>
			<xsl:value-of select="$counter*15"/>
		</xsl:otherwise>
	</xsl:choose>
	
</xsl:template>
	
</xsl:stylesheet>