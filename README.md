# Similarity join

The goal of the project is to implement distributed local sensitivity hashing technique in map-reduce model.
Local sensitivity hashing is a technique for finding similar sets of element.
After we have found pairs of potentially similar elements, we can measure their rate of similarity using different measurements, e.g. Jaccard Similar, Hamming Distance(for text documents)

We are going to work with a document of tweets, where each tweet is a single line of the format
- tweet_id, tweet_content

and try to compute different similarity measures between tweets with odd id and tweets with even id

### Reference
- local sensitivity hashing - https://en.wikipedia.org/wiki/Locality-sensitive_hashing
- minhashing - https://en.wikipedia.org/wiki/MinHash
- jaccard similarity - https://en.wikipedia.org/wiki/Jaccard_index
- hamming distance - https://en.wikipedia.org/wiki/Hamming_distance


