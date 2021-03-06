import scala.collection.mutable.HashMap
object Main {

  def main(args: Array[String]): Unit = {
    val sequence = "TCAAGAAAATTTAAATAATCACATTTTTATTATTTTAGATTT" +
      "AAGTATTGATACAAGTGATATCTATAAATGTTTTTATAACTTTCTGGATCGTAATCGGCTGGCAATCGTT" +
      "TTCCCTATATTCGCAAGATGTATCTCAGCCGCAGATTTGTCGACTGACCTCTATCTCTCCGAGATATATCA" +
      "ACAGGAGGTAGTCACCATGAAAGCAGCCGTCATAACTAAAGATCATACGATCGAAGTGAAAGACACCAAGTT" +
      "ACGCCCTCTGAAATACGGGGAAGCGCTTTTGGAAATGGAATATTGCGGGGTCTGTCATACCGATCTCCACGTGA" +
      "AAAACGGAGATTTTGGCGATGAAACCGGCAGAATTACCGGCCACGAAGGCATCGGCATCGTCAAGCAGGTCGGGG" +
      "AAGGGGTTACTTCACTGAAAGTCGGTGACCGTGCCAGTGTAGCATGCTTCTTCAAAGGCTGCGGCCATTGCGAAT" +
      "ATTGTGTCAGTGGAAATGAAACGCTTTGCCGCAACGTTGAGAATGCCGGTTATACGGTTGACGGCGCTATGGCA" +
      "GAAGAATGCATCGTCGTTGCCGATTACTCGGTCAAAGTGCCAGATGGTCTTGATCCTGCGGTTGCCAGCAGCAT" +
      "CACTTGCGCGGGTGTAACCACCTATAAAGCAGTCAAAGTTTCTCAGATACAGCCGGGACAATGGCTGGCTATCTA" +
      "TGGCTTGGGCGGTTTAGGCAATCTAGCCCTTCAATATGCCAAGAATGTTTTCAACGCCAAAGTGATCGCGATCGATG" +
      "TCAATGATGAACAACTCGCTTTTGCCAAAGAGCTGGGCGCAGATATGGTCATCAATCCGAAAAACGAAGATGCTGTCG" +
      "CAAAATCATTCAGGAAAAAGTCGGCGGCGCACACGCGACGGTGGTGACAGCTGTTGCCAAATCCGCCTTTAACTCGGCTGT" +
      "TGAGGCTATCCGCGCGGGTGGCCGTGTTGTCGCCGTAGGTCTGCCTCCTGAAAAAATGGATTTGAGCATTCCTCGCTTGG" +
      "TGCTTGACGGTATCGAAGTCTTAGGTTCCTTGTTCGGAACGCGGGAAGATTTGAAAGAAGCCTTCCAGTTTGCAGCCGAAGG" +
      "TAAGGTCAAACCGAAAGTCACCAAGCGTAAAGTCGAAGAAATCAACCAAATCTTTGACGAAATGGAACATGGTAAATTCACA" +
      "GGCCGTATGGTTGTTGATTTTACCCATCACTAGGTTTCCGTGAAGGCGGAAGCATAAACGGAAAAAGCCTTTCTCTTACCAG" +
      "AAAGGCTTTTTCTTTGTCGTCTGATAAAAATTTTCAT"
    val ORFArray: HashMap[String, String] = run(sequence)
    print(ORFArray.get("ORF1"))
  }

  def run(sequence:String): HashMap[String, String] = {
    var sequenceNr: Int = 0
    var ORF: StringBuilder = new StringBuilder
    var ORFArray: HashMap[String, String] = new HashMap[String, String]()
    var sequenceFound: Boolean = false
    while (sequenceNr < sequence.length - 2) {
      var currentCodon: String = (sequence.charAt(sequenceNr).toString + sequence.charAt(sequenceNr + 1).toString + sequence.charAt(sequenceNr + 2)).toString
      
      if (currentCodon.equals("ATG")) {
        ORF += 'A'
        ORF += 'T'
        ORF += 'G'
        sequenceNr += 3
        sequenceFound = true
      }
      else if ((currentCodon.equals("TAA")||(currentCodon.equals("TAG"))||(currentCodon.equals("TGA"))) && sequenceFound.equals(true))  {
        
        ORF += currentCodon.charAt(0)
        ORF += currentCodon.charAt(1)
        ORF += currentCodon.charAt(2)
        
        if (ORF.length() > 50) {
          ORFArray.put("ORF" + ORFArray.size, ORF.toString())
        }
        ORF = new StringBuilder
        sequenceNr += 1
        sequenceFound = false
      }
      else {
        if (sequenceFound) {
          ORF += currentCodon.charAt(0)
          ORF += currentCodon.charAt(1)
          ORF += currentCodon.charAt(2)
          sequenceNr+=3
        }else sequenceNr += 1
      }
    }
    return ORFArray
  }
  
  
  
}
