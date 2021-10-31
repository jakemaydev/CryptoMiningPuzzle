# CryptoMiningPuzzle
Small application utilising SHA-256 hashing to solve a typical blockchain hashing puzzle

Interesting project as it forced me to learn and use the more complicated BigInteger and BigDecimal rather than their primitive counterparts. This is something I was going to implement as a conversion exercise in my pi estimation program.

To compare the value of my BigInteger inside the loop as my break condition, I had to cast it to a BigDecimal, (as well as my double t value) as comparing BigInteger objects isn't supported.

TODO:
- Add tests
