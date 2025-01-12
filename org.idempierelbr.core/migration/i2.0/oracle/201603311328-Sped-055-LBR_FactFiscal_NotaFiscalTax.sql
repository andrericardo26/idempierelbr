-- Sped EFD
-- This script was generated for a postgresql DB. It should be tested in a oracle DB.
CREATE OR REPLACE VIEW LBR_FactFiscal_NotaFiscalTax AS
SELECT nft.lbr_notafiscal_id,
    t.name LBR_Tax_Name,
    nft.taxamt,
    nft.taxbaseamt
   FROM lbr_notafiscaltax nft
     JOIN c_tax t ON nft.c_tax_id = t.c_tax_id
  WHERE nft.isactive = 'Y' AND nft.processed = 'Y';
  
SELECT lbr_register_migration_script('201603311328-Sped-055-LBR_FactFiscal_NotaFiscalTax.sql') FROM dual;
